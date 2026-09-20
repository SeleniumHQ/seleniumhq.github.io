---
title: "Selenium Grid 4.41.0: What's New and Why It Matters"
linkTitle: "Selenium Grid 4.41.0: What's New and Why It Matters"
date: 2026-02-22
tags: [ "Grid", "Docker", "Kubernetes", "KEDA"  ]
categories: [ "Releases", "Technical", "Docker" ]
author: Viet Nguyen Duc [@VietND96](https://github.com/VietND96)
images:
  - "/images/blog/2026/selenium-grid-4-41-deep-dive.jpg"
description: >
  We are excited to ship Selenium Grid 4.41.0 — and this release brings something meaningful for you
---

We are excited to ship Selenium Grid **4.41.0** 🎉 — and this might be one of impactful releases in recent memory. Whether you are running Grid in a bare-metal lab, a Docker Compose stack, or a sprawling Kubernetes cluster, this release brings something meaningful for you. From a brand-new **Dynamic Grid for Kubernetes**, a powerful **Session Event API**, to smarter video recording and a rock-solid Distributor, let's dig in.


---

## 1. Dynamic Grid, Now Native on Kubernetes

The headline feature of 4.41.0 is unambiguous: **Dynamic Grid now runs natively inside Kubernetes clusters** ([selenium#17092](https://github.com/SeleniumHQ/selenium/pull/17092), [docker-selenium#3082](https://github.com/SeleniumHQ/docker-selenium/pull/3082)).

### Background

Dynamic Grid is the architecture where Selenium Grid provisions browser containers **on demand** per test session — no pre-warmed browser pods sitting idle, no slot over-allocation, no manual scaling. Before 4.41.0, this capability was only available when the Grid node ran alongside a Docker daemon, making it a Docker-first feature.

For Kubernetes users, scaling the Grid has long meant one of two paths: static node deployments with fixed slot pools, or integrating [KEDA](https://keda.sh/) with the [Selenium Grid Scaler](https://keda.sh/docs/latest/scalers/selenium-grid-scaler/). The KEDA approach has matured considerably over the years and works well — but it comes with a certain complexity. Users must reason about and maintain two separate systems simultaneously: KEDA's ScaledObject configuration and the Grid's own node and session settings. Getting them to agree on scale-up thresholds, cooldown periods, and session slot counts requires ongoing attention, and any Grid upgrade potentially touches both sides.

Dynamic Grid for Kubernetes takes a different approach: scaling is an intrinsic behaviour of the Grid itself, not an external concern. The Grid provisions exactly one browser Pod per session request and deletes it immediately on close. There is no separate scaler to configure or keep in sync.

With 4.41.0, we introduce `KubernetesSessionFactory` — a full Kubernetes-aware session factory that creates ephemeral browser **Pods** on the fly per session request, and tears them down immediately when the session closes.

### What's new in the core (Java)

Underpinning this entire feature is a new Service Provider Interface (SPI): [`NodeSessionFactoryProvider`](https://github.com/SeleniumHQ/selenium/blob/selenium-4.41.0/java/src/org/openqa/selenium/grid/node/NodeSessionFactoryProvider.java). This interface lets anyone ship a custom session factory as an external jar, loaded at runtime via `--ext`, without touching `selenium-server.jar`:

```java
public interface NodeSessionFactoryProvider {
  /** Return true when this provider's config section is present. */
  boolean isEnabled(Config config);

  /** Return the capability→factory mappings this provider contributes. */
  Map<Capabilities, Collection<SessionFactory>> loadFactories(
      Config config, Tracer tracer, HttpClient.Factory clientFactory);
}
```

`LocalNodeFactory` discovers all implementations on the classpath via `ServiceLoader` at startup — if `isEnabled()` returns true, the factories are merged into the node's slot pool. This keeps the core jar lean and opens the door for community extensions targeting any container runtime, cloud provider, or custom provisioning system.

The Kubernetes support in this release is the first implementation of this SPI. A new `kubernetes` sub-package under `grid/node` brings:

- [`KubernetesSessionFactoryProvider.java`](https://github.com/SeleniumHQ/selenium/blob/selenium-4.41.0/java/src/org/openqa/selenium/grid/node/kubernetes/KubernetesSessionFactoryProvider.java) — the `@AutoService`-annotated SPI entry point; `isEnabled()` activates when `[kubernetes] configs` is present in config
- [`KubernetesSessionFactory.java`](https://github.com/SeleniumHQ/selenium/blob/selenium-4.41.0/java/src/org/openqa/selenium/grid/node/kubernetes/KubernetesSessionFactory.java) — orchestrates Pod lifecycle via the official Kubernetes Java client
- [`KubernetesOptions.java`](https://github.com/SeleniumHQ/selenium/blob/selenium-4.41.0/java/src/org/openqa/selenium/grid/node/kubernetes/KubernetesOptions.java) — configuration model for namespace, pod specs, resource limits, tolerations, node selectors, image pull secrets, and more
- [`InheritedPodSpec.java`](https://github.com/SeleniumHQ/selenium/blob/selenium-4.41.0/java/src/org/openqa/selenium/grid/node/kubernetes/InheritedPodSpec.java) — propagates the Node Pod's own spec fields (service account, tolerations, affinity, PVCs) to browser Jobs automatically
- [`KubernetesFlags.java`](https://github.com/SeleniumHQ/selenium/blob/selenium-4.41.0/java/src/org/openqa/selenium/grid/node/kubernetes/KubernetesFlags.java) — CLI flags under the `--kubernetes-*` prefix, mirroring every option in the TOML `[kubernetes]` section
- A new help text file [`kubernetes.txt`](https://github.com/SeleniumHQ/selenium/blob/selenium-4.41.0/java/src/org/openqa/selenium/grid/commands/kubernetes.txt) documenting all configuration options

### New Docker images

Two new container images land in docker-selenium:

- [`NodeKubernetes`](https://github.com/SeleniumHQ/docker-selenium/tree/4.41.0-20260222/NodeKubernetes) ([Docker Hub](https://hub.docker.com/r/selenium/node-kubernetes)) — the Dynamic Grid node that talks to the Kubernetes API
- [`StandaloneKubernetes`](https://github.com/SeleniumHQ/docker-selenium/tree/4.41.0-20260222/StandaloneKubernetes) ([Docker Hub](https://hub.docker.com/r/selenium/standalone-kubernetes)) — a fully self-contained single-binary Standalone in Kubernetes mode

Along with them, reference Kubernetes manifests are available at [`kubernetes/DynamicGrid/`](https://github.com/SeleniumHQ/docker-selenium/tree/4.41.0-20260222/kubernetes/DynamicGrid). These are intentionally simplex — designed for local practice and getting started quickly, not as production blueprints:

```
kubernetes/DynamicGrid/
├── BaseConfig/
│   ├── configmap.yaml   # Grid config, browser stereotypes
│   ├── pvc.yaml         # Shared video/asset storage
│   └── rbac.yaml        # ServiceAccount + ClusterRole to create/delete Pods
├── Hub_Node/
│   ├── hub-deployment.yaml
│   ├── hub-svc.yaml
│   └── node-kubernetes-deployment.yaml
└── Standalone/
    └── standalone-kubernetes.yaml
```

### Minimal Hub+Node setup

Browser stereotypes and Dynamic Grid tuning live in a TOML config file, delivered to the Node Pod via a [ConfigMap](https://github.com/SeleniumHQ/docker-selenium/blob/4.41.0-20260222/kubernetes/DynamicGrid/BaseConfig/configmap.yaml):

```yaml
# configmap.yaml
apiVersion: v1
kind: ConfigMap
metadata:
  name: selenium-kubernetes-config
data:
  kubernetes.toml: |
    [kubernetes]
    configs = [
        "selenium/standalone-chrome:4.41.0-20260222", '{"browserName": "chrome", "platformName": "linux"}',
        "selenium/standalone-firefox:4.41.0-20260222", '{"browserName": "firefox", "platformName": "linux"}',
        "selenium/standalone-edge:4.41.0-20260222", '{"browserName": "MicrosoftEdge", "platformName": "linux"}'
    ]
```

The `configs` array pairs each browser image with a capability stereotype JSON string. The Node uses these to match incoming session requests against the right image, spin up the Pod, and report available slots to the Hub.

The [Node deployment](https://github.com/SeleniumHQ/docker-selenium/blob/4.41.0-20260222/kubernetes/DynamicGrid/Hub_Node/node-kubernetes-deployment.yaml) then mounts that ConfigMap as a file and points the Grid node at it:

```yaml
# node-kubernetes-deployment.yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: selenium-node-kubernetes
spec:
  replicas: 1
  template:
    spec:
      serviceAccountName: selenium-node        # needs Pod create/delete RBAC
      terminationGracePeriodSeconds: 300
      containers:
        - name: selenium-node-kubernetes
          image: selenium/node-kubernetes:4.41.0-20260222
          ports:
            - containerPort: 5555
          env:
            - name: SE_EVENT_BUS_HOST
              value: "selenium-hub"
            - name: SE_NODE_SESSION_TIMEOUT
              value: "600"
            - name: SE_DYNAMIC_OVERRIDE_MAX_SESSIONS
              value: "true"
            - name: SE_DYNAMIC_MAX_SESSIONS
              value: "10"
          volumeMounts:
            - name: selenium-config
              mountPath: /opt/selenium/kubernetes.toml  # TOML config path
              subPath: kubernetes.toml
              readOnly: true
            - name: session-assets                      # shared PVC for video/logs
              mountPath: /opt/selenium/assets
      volumes:
        - name: selenium-config
          configMap:
            name: selenium-kubernetes-config            # references the ConfigMap above
        - name: session-assets
          persistentVolumeClaim:
            claimName: selenium-assets
```

Event bus connectivity (host), and node config (session timeout) are passed as environment variables. Browser stereotypes live entirely in the TOML file inside the ConfigMap, keeping them independently updatable without redeploying the Node.

The [RBAC manifest](https://github.com/SeleniumHQ/docker-selenium/blob/4.41.0-20260222/kubernetes/DynamicGrid/BaseConfig/rbac.yaml) grants the Node the minimal permissions needed — `create`, `delete`, and `get` on `pods` and `pods/log` in the configured namespace. No cluster-wide permissions needed.

### Scaling strategy with InheritedPodSpec

[`InheritedPodSpec`](https://github.com/SeleniumHQ/selenium/blob/selenium-4.41.0/java/src/org/openqa/selenium/grid/node/kubernetes/InheritedPodSpec.java) is what makes multi-Node deployments particularly powerful. When the Dynamic Grid Node runs inside Kubernetes, it inspects its own Pod spec and automatically propagates matching fields to every browser Job it creates — tolerations, affinity rules, node selectors, resource requests and limits, image pull secrets, service account, DNS config, security context, and PVC mounts at the assets path.

This means the browser Pods land on exactly the same node pool, zone, or hardware tier as the Dynamic Node that spawned them — with no extra configuration required in the TOML or ConfigMap.

The practical implication: you can deploy **multiple Dynamic Grid Nodes**, each pinned to a different cluster segment via their own `nodeSelector` or `affinity`, and all registered to the same Hub. The Hub distributes session requests across them, and each Node provisions browser Pods that inherit its own placement constraints.

```
                         ┌─────────────────────────────────-┐
                         │             Hub                  │
                         └──────┬──────────────┬───────────-┘
                                │              │
               ┌────────────────▼──┐      ┌───-▼────────────────┐
               │ NodeKubernetes    │      │ NodeKubernetes      │
               │ nodeSelector:     │      │ nodeSelector:       │
               │   zone=us-west    │      │   zone=us-east      │
               └────────┬──────────┘      └────────-┬───────────┘
                        │ inherits spec             │ inherits spec
              ┌─────────▼──────────┐    ┌──────────-▼──────────┐
              │  browser Pod       │    │  browser Pod         │
              │  (us-west, same    │    │  (us-east, same      │
              │   tolerations...)  │    │   tolerations...)    │
              └────────────────────┘    └─────────────────────-┘
```

Teams can keep multiple Dynamic Nodes on standby — lightweight processes consuming minimal resources — and let the browser Pods scale horizontally on demand within each segment. This approach fits naturally into cluster autoscaler workflows: idle Nodes hold their spot in the cluster; browser Jobs drive the actual compute scaling.

This is a genuinely exciting milestone. Teams running large Kubernetes fleets can now enjoy true on-demand browser provisioning without a Docker socket sidecar. The browser Pod starts when the session starts, and disappears the moment it ends. Resources are consumed only when tests are actually running.

---

## 2. Session Event API: A Bridge to the Outside World

The second headline feature is the **Session Event API** ([selenium#17015](https://github.com/SeleniumHQ/selenium/pull/17015)).

### The problem it solves

Many teams have built tooling around Selenium Grid — dashboards, alerting systems, test analytics platforms, video archival pipelines. Before this release, there was no stable way for test code to signal custom events to the Grid infrastructure layer. You couldn't tell the video recorder "this test just failed, add a marker" or tell a log collector "the interesting part is about to start" without building your own out-of-band channel.

4.41.0 introduces a proper, documented **client-to-grid event push endpoint** that lets test code fire named events with arbitrary payloads directly through the Grid node, onto the internal event bus where sidecar services can react.

### How it works

The new endpoint lives on the Node, scoped to an active session:

```
POST /session/{sessionId}/se/event
```

The request body carries an `eventType` (a namespaced string) and an optional `payload` map:

```json
{
  "eventType": "test:failed",
  "payload": {
    "testName": "LoginTest",
    "error": "Element not found",
    "screenshot": true
  }
}
```

The Node validates the event, enriches it with `nodeId`, `nodeUri`, and `timestamp`, then fires a [`SessionEvent`](https://github.com/SeleniumHQ/selenium/blob/selenium-4.41.0/java/src/org/openqa/selenium/grid/data/SessionEvent.java) onto the ZeroMQ event bus. Sidecar services — like the video recorder — subscribe to the `session-event` topic on the same bus and react accordingly. The full wire format of the enriched [`SessionEventData`](https://github.com/SeleniumHQ/selenium/blob/selenium-4.41.0/java/src/org/openqa/selenium/grid/data/SessionEventData.java) published on the bus:

```json
{
  "sessionId": "abc123",
  "eventType": "test:failed",
  "nodeId": "node-uuid",
  "nodeUri": "http://node:5555",
  "timestamp": "2026-02-24T10:00:00Z",
  "payload": { "testName": "LoginTest", "error": "Element not found" }
}
```

`eventType` follows a `namespace:action` convention (e.g., `test:started`, `test:failed`, `log:collect`, `marker:add`). The value must start with a letter and contain only alphanumerics, colons, dots, underscores, or hyphens — anything else is rejected at the Node.

### Multi-language client support

Firing events is surfaced directly on the driver instance in **all five official language bindings** (required binding version 4.41.0+ also). No manual HTTP wiring needed — the driver knows the session ID and Grid URL:

```python
# Python
driver.fire_session_event("test:started")
driver.fire_session_event("test:failed", {"testName": "LoginTest", "error": "Element not found"})
```

```java
// Java
driver.fireSessionEvent("test:started");
driver.fireSessionEvent("test:failed", Map.of("testName", "LoginTest", "error", "Element not found"));
```

```javascript
// JavaScript
await driver.fireSessionEvent('test:started');
await driver.fireSessionEvent('test:failed', { testName: 'LoginTest', error: 'Element not found' });
```

```ruby
# Ruby
driver.fire_session_event("test:started")
driver.fire_session_event("test:failed", { testName: "LoginTest", error: "Element not found" })
```

```csharp
// .NET
driver.FireSessionEvent("test:started");
driver.FireSessionEvent("test:failed", new Dictionary<string, object>
{
    { "testName", "LoginTest" },
    { "error", "Element not found" }
});
```

Under the hood each binding issues a `POST /session/{sessionId}/se/event`. You can also call it directly via cURL for scripting or debugging:

```bash
curl -X POST http://localhost:4444/session/${SESSION_ID}/se/event \
  -H 'Content-Type: application/json' \
  -d '{"eventType": "test:failed", "payload": {"testName": "LoginTest"}}'
```

### Sidecar consumption via ZeroMQ

The receiving side subscribes directly to the Grid's ZeroMQ event bus — the same bus used by `SessionCreatedEvent` and `SessionClosedEvent`. The [`video_service.py`](https://github.com/SeleniumHQ/docker-selenium/blob/4.41.0-20260222/Video/video_service.py) already does this for the three built-in topics:

```python
# video_service.py (simplified)
for event in ["session-created", "session-closed", "session-event"]:
    self.subscriber.setsockopt_string(zmq.SUBSCRIBE, event)
```

This is not limited to Hub+Node setups. **Standalone** can expose the same publish and subscribe ports so any sidecar can tap into the event bus alongside it:

```bash
java -jar selenium-server.jar standalone \
  --bind-bus true \
  --events-implementation "org.openqa.selenium.events.zeromq.ZeroMqEventBus" \
  --publish-events "tcp://*:4442" \
  --subscribe-events "tcp://*:4443"
```

With `--bind-bus true`, Standalone starts its own ZeroMQ event bus and exposes it on the configured ports — the same ports your sidecar would connect to for subscriptions. This makes it straightforward to build any sidecar that reacts to lifecycle events and custom test signals through a single subscription, regardless of whether you are running a full Grid cluster or a single Standalone container.

This event-driven foundation also opens the door for docker-selenium to continue expanding its built-in tooling. Two natural directions on the roadmap: recording headless browser sessions that today have no display to capture, and recording trace views by connecting to the session's WebSocket CDP endpoint at the right moment in the session lifecycle — both of which become straightforward sidecars once you know precisely when a session starts and which CDP endpoint it exposes.

---

## 3. Event-Driven Video Recording — Now the Default

Video recording in docker-selenium has grown significantly smarter, and in 4.41.0 it becomes **the default behavior** ([docker-selenium#3070](https://github.com/SeleniumHQ/docker-selenium/pull/3070), [docker-selenium#3084](https://github.com/SeleniumHQ/docker-selenium/pull/3084)).

### Before: timer-based recording

Previously, the Video container used a timer-based heuristic to decide when to start and stop recording. For Grid Hub/Node setups this was manageable, but for **Standalone** mode it was fragile — the recorder had no reliable way to know when a session started or ended. It would sometimes start too early or cut off the last frames.

### After: event-driven recording

With the new [`Video/video_service.py`](https://github.com/SeleniumHQ/docker-selenium/blob/4.41.0-20260222/Video/video_service.py), the video container subscribes to the Grid's **ZeroMQ event bus** directly. When a `session-created` event arrives, recording starts. When `session-closed` fires, recording stops and the uploader kicks in.

```
[Selenium Node] ──ZeroMQ──► [video_service.py] ──start/stop──► [FFmpeg recorder]
  (event bus)   session-created / session-closed / session-event  └──upload──► [S3/GCS/Azure]
```

`SE_VIDEO_EVENT_DRIVEN=true` is now the default. The old polling-based mode is still available for backward compatibility by setting `SE_VIDEO_EVENT_DRIVEN=false`.

**Standalone mode** gets special handling ([docker-selenium#3089](https://github.com/SeleniumHQ/docker-selenium/pull/3089)): when Standalone is configured with basic auth, the event-driven service now correctly passes credentials when polling the `/status` endpoint used to verify readiness before subscribing to events.

### Upload only failed sessions with SE_UPLOAD_FAILURE_SESSION_ONLY

This is where the event-driven architecture pays off in a particularly practical way for storage costs.

Set `SE_UPLOAD_FAILURE_SESSION_ONLY=true` and the uploader will **skip cloud upload for sessions that passed** — only failed or abnormally-closed sessions get their video uploaded. In a large test suite where the vast majority of tests pass, this can cut video storage significantly.

A session is treated as failed under two conditions:

1. **Custom failure event fired** — your test framework calls `driver.fire_session_event("test:failed", ...)` during the session. The sidecar matches the event type against a configurable list of failure patterns.
2. **Abnormal close reason** — the `session-closed` event is received but carries a `reason` other than `QUIT_COMMAND`. Currently, that means `TIMEOUT` (session exceeded the configured timeout) or `NODE_REMOVED` (the node was forcibly removed from the Grid). A clean `driver.quit()` sends `QUIT_COMMAND` and is never treated as a failure.

The failure event type matching is controlled by `SE_UPLOAD_FAILURE_SESSION_EVENTS` (default: `:failed,:failure`). The match is a substring check on the event type, so the default catches `test:failed`, `suite:failed`, `step:failure`, etc. You can extend the list for your own conventions:

```bash
# Upload only videos where test:failed, test:error, or test:aborted were fired
SE_UPLOAD_FAILURE_SESSION_ONLY=true
SE_UPLOAD_FAILURE_SESSION_EVENTS=":failed,:failure,:error,:aborted"
```

The full flow from test code to selective upload:

```
driver.fire_session_event("test:failed", {"testName": "LoginTest"})
        │
        ▼ POST /session/{id}/se/event  (Node HTTP endpoint)
        │
        ▼ ZeroMQ  topic: session-event
        │
        ▼ video_service.py marks session.is_failed = True
        │
        ▼ on session-closed: upload because is_failed == True
                             (skip upload if is_failed == False and upload_failure_only == True)
```

This makes the Session Event API and event-driven video a cohesive feature pair: the API is not just a notification mechanism — it directly drives infrastructure decisions like whether a video is worth keeping.

### What this means for you

Recordings are now **precisely bounded** to the session lifecycle. No more partial recordings, no blank frames at the start, no abrupt cuts at the end. For teams uploading videos to cloud storage as test artifacts, the uploader now reliably fires exactly once per session, immediately on close — and with `SE_UPLOAD_FAILURE_SESSION_ONLY`, only the sessions that matter.

---

## 4. Traefik Replaces Ingress NGINX as the Default Helm Ingress Controller

If you deploy Selenium Grid via Helm, take note: **Traefik is now the default ingress controller** ([docker-selenium#3083](https://github.com/SeleniumHQ/docker-selenium/pull/3083)).

### Why the switch?

The timing is deliberate. Per the [official Kubernetes blog announcement in November 2025](https://kubernetes.io/blog/2025/11/11/ingress-nginx-retirement/), the Ingress NGINX Helm chart will no longer be maintained after March 2026. With that EOL approaching, now is the right moment to move rather than wait for users to be stranded on an unmaintained dependency.

Traefik is a strong candidate to fill that gap, and it brings several advantages specifically for Selenium Grid's traffic patterns:

- **Native WebSocket support** — critical for BiDi (WebDriver BiDi) and CDP proxying through Grid, with no special annotation gymnastics
- **Built-in middleware** — Traefik's `IngressRoute` and middleware CRDs allow expressing Grid's routing requirements (path rewrites, basic auth, sticky sessions) more cleanly
- **Better observability** — native Prometheus metrics endpoint out of the box

The PR includes a comprehensive [`MIGRATION_INGRESS_NGINX_TO_TRAEFIK.md`](https://github.com/SeleniumHQ/docker-selenium/blob/4.41.0-20260222/charts/selenium-grid/MIGRATION_INGRESS_NGINX_TO_TRAEFIK.md) guide covering all common migration scenarios.

### Migration in brief

```yaml
# values.yaml — before
ingress:
  enabled: true
  className: "nginx"
  annotations:
    nginx.ingress.kubernetes.io/proxy-read-timeout: "3600"

# values.yaml — after
ingress:
  enabled: true
  className: "traefik"
  # Traefik handles WS and timeouts natively via ServersTransport
```

A new [`traefik-servers-transport.yaml`](https://github.com/SeleniumHQ/docker-selenium/blob/4.41.0-20260222/charts/selenium-grid/templates/traefik-servers-transport.yaml) template is included in the chart, pre-configured with appropriate dial and response timeouts for Selenium Grid workloads.

---

## 5. Distributor Reliability — Serious Under-the-Hood Work

Three significant Distributor fixes ship in 4.41.0, addressing real concurrency issues that could manifest under load.

**Fix 1: Thread exhaustion in health-check cycle ([selenium#17104](https://github.com/SeleniumHQ/selenium/pull/17104))**

The `LocalDistributor` runs periodic health checks against all registered nodes. Under high node counts, a subtle bug caused the health-check executor thread pool to accumulate tasks faster than they were being consumed, eventually exhausting threads and causing new session requests to stall.

The fix refactors [`LocalNodeRegistry.java`](https://github.com/SeleniumHQ/selenium/blob/selenium-4.41.0/java/src/org/openqa/selenium/grid/distributor/local/LocalNodeRegistry.java) to decouple the health-check scheduling from the main distribution path, with new tests specifically exercising concurrent health-check scenarios.

**Fix 2: WebSocket connection counter leaks ([selenium#17106](https://github.com/SeleniumHQ/selenium/pull/17106))**

[`ProxyNodeWebsockets`](https://github.com/SeleniumHQ/selenium/blob/selenium-4.41.0/java/src/org/openqa/selenium/grid/node/ProxyNodeWebsockets.java) tracks active WebSocket connections per node to respect `maxSessions`. A race condition in the connection bookkeeping could cause the counter to drift upward, making slots appear occupied when they were free. Over time this would cause nodes to appear artificially full.

The fix tightens the lifecycle management with try-finally guards around counter decrements.

**Fix 3: Retry session on executor shutdown ([selenium#17109](https://github.com/SeleniumHQ/selenium/pull/17109))**

When a [`RemoteNode`](https://github.com/SeleniumHQ/selenium/blob/selenium-4.41.0/java/src/org/openqa/selenium/grid/node/remote/RemoteNode.java)'s thread executor enters a shutdown state (e.g., during a graceful drain), session creation requests could be silently dropped instead of being returned to the queue for redistribution. The Distributor now detects `RejectedExecutionException` from a shutting-down executor and transparently retries the session on another available node.

**Deadlock prevention ([selenium#17022](https://github.com/SeleniumHQ/selenium/pull/17022))**

[`LocalGridModel`](https://github.com/SeleniumHQ/selenium/blob/selenium-4.41.0/java/src/org/openqa/selenium/grid/distributor/local/LocalGridModel.java) contained a lock inversion risk between its internal state lock and the event bus listener lock. Under specific timing conditions this could deadlock the Distributor entirely. The fix restructures lock acquisition order with a dedicated test ([`LocalGridModelDeadlockTest.java`](https://github.com/SeleniumHQ/selenium/blob/selenium-4.41.0/java/test/org/openqa/selenium/grid/distributor/local/LocalGridModelDeadlockTest.java)) that explicitly reproduces the hazard.

These four fixes together make the Distributor measurably more robust at scale. If you have ever seen mysterious session queue stalls or nodes appearing full when they shouldn't be, upgrading to 4.41.0 is strongly recommended.

---

## 6. Docker Selenium as a Browser Version Matrix

Before getting into the remaining improvements, this is a good moment to highlight something that often goes unnoticed by newcomers: docker-selenium is not just an infrastructure tool — it is also a **versioned browser library**.

A standalone container behaves like a lightweight virtual machine. You pull a specific image tag, start it, and you have an isolated browser environment with a precise browser version and matching driver, ready to connect over WebDriver. No installation, no system-level conflicts, no flakiness from browser auto-updates.

```bash
# Reproduce a bug against Chrome 118 specifically
docker run -d -p 4444:4444 -p 5900:5900 selenium/standalone-chrome:118.0-20260222

# Run your test
pytest tests/regression/login_test.py --grid http://localhost:4444

# You also can use a VNC client and connect to host port 5900 to interact like a virtual machine
```

With 4.41.0, docker-selenium ships **167+ image variants** across four browser families — Chrome, Chrome for Testing, Edge, and Firefox — spanning versions 95 through 148. The full matrix for every release is tracked in the [`CHANGELOG/`](https://github.com/SeleniumHQ/docker-selenium/tree/trunk/CHANGELOG) directory, where each browser version gets its own file listing the exact image tags, browser version, and driver version bundled together.

This makes docker-selenium the go-to tool whenever you need to:
- Reproduce a user-reported bug on a specific browser version without touching your local machine
- Run a cross-version compatibility sweep in CI across a range of browser versions in parallel
- Pin a version in a test environment and guarantee it never changes until you deliberately upgrade

**Interactive debugging with Fluxbox browser menu ([docker-selenium#3085](https://github.com/SeleniumHQ/docker-selenium/pull/3085))**

Complementing the above, the Fluxbox desktop inside every node and standalone image now has a right-click menu with browser launchers and a lightweight terminal (xterm). Connect to the container via VNC or noVNC, right-click the desktop, and launch the browser directly — no command line needed. When you are debugging a test failure visually against a specific image version, this removes the last friction from the interactive workflow.

---

## 7. More Improvements Worth Knowing About

**Unified Dynamic Grid configuration (Docker & Kubernetes) ([docker-selenium#3088](https://github.com/SeleniumHQ/docker-selenium/pull/3088))**

Configuration environment variables are now shared across [`NodeDocker`](https://github.com/SeleniumHQ/docker-selenium/tree/4.41.0-20260222/NodeDocker), [`StandaloneDocker`](https://github.com/SeleniumHQ/docker-selenium/tree/4.41.0-20260222/StandaloneDocker), [`NodeKubernetes`](https://github.com/SeleniumHQ/docker-selenium/tree/4.41.0-20260222/NodeKubernetes), and [`StandaloneKubernetes`](https://github.com/SeleniumHQ/docker-selenium/tree/4.41.0-20260222/StandaloneKubernetes) images. Variables like `SE_NODE_GRID_URL`, `SE_NODE_MAX_SESSIONS`, and video-related settings behave identically regardless of whether the Dynamic Grid backend is Docker or Kubernetes.

**Basic auth support in Dynamic Grid Standalone ([selenium#17072](https://github.com/SeleniumHQ/selenium/pull/17072))**

When Grid Standalone is secured with HTTP basic auth, [`DockerSessionFactory`](https://github.com/SeleniumHQ/selenium/blob/selenium-4.41.0/java/src/org/openqa/selenium/grid/node/docker/DockerSessionFactory.java) now correctly forwards credentials when communicating with the node's own status endpoint during session setup. A small but important fix that unblocks secured Dynamic Grid deployments.

**Restore stereotype capability merging in RelaySessionFactory ([selenium#17097](https://github.com/SeleniumHQ/selenium/pull/17097))**

A regression introduced in a prior release caused [`RelaySessionFactory`](https://github.com/SeleniumHQ/selenium/blob/selenium-4.41.0/java/src/org/openqa/selenium/grid/node/relay/RelaySessionFactory.java) to ignore stereotype capabilities during session creation, which broke mobile relay sessions that relied on custom capabilities from the stereotype being merged into the session request.

**Kubernetes: structured logs support ([docker-selenium#3087](https://github.com/SeleniumHQ/docker-selenium/pull/3087))**

Structured logging (`global.seleniumGrid.structuredLogs`) already existed, but plain-text logs were always emitted alongside it with no way to suppress them. This PR adds `SE_PLAIN_LOGS` (`--plain-logs`, default `true`) as an independent toggle. Both modes can run simultaneously — to get pure JSON output for a log aggregation pipeline (Loki, Elasticsearch), enable structured logs and turn plain logs off:

```yaml
# Helm values.yaml
global:
  seleniumGrid:
    structuredLogs: true
    plainLogs: false
```

Or via environment variable on any component directly:

```bash
SE_PLAIN_LOGS=false
```

**Kubernetes: tolerations fix for monitoring exporter ([docker-selenium#3086](https://github.com/SeleniumHQ/docker-selenium/pull/3086))**

The Helm chart's monitoring exporter deployment was missing `tolerations`, which caused it to fail scheduling on tainted nodes (a common pattern in production clusters with dedicated node pools). This is now fixed.

---

## Upgrading

### Docker Compose

```bash
# Pull latest images
docker pull selenium/hub:4.41.0-20260222
docker pull selenium/node-chrome:4.41.0-20260222
docker pull selenium/video:ffmpeg-8.1-20260222
```

Or update your compose file:

```yaml
services:
  chrome:
    image: selenium/node-chrome:4.41.0-20260222
```

### Helm

```bash
helm repo update
helm upgrade selenium-grid docker-selenium/selenium-grid \
  --version 0.52.0 \
  --reuse-values
```

> **Helm users**: Review the [Ingress NGINX to Traefik migration guide](https://github.com/SeleniumHQ/docker-selenium/blob/4.41.0-20260222/charts/selenium-grid/MIGRATION_INGRESS_NGINX_TO_TRAEFIK.md) before upgrading if you have custom ingress configuration.

### Standalone JAR

```bash
# Standard standalone
java -jar selenium-server.jar standalone
```

The Kubernetes Dynamic Grid module ships as a **separate extension jar** that is loaded at runtime via `--ext`. The simplest way to fetch it is with [Coursier](https://get-coursier.io/):

```bash
# Node with Dynamic Grid on Kubernetes (image mode)
java -jar selenium-server.jar \
  --ext $(coursier fetch -p org.seleniumhq.selenium:selenium-node-kubernetes:latest.release) \
  node \
  -K selenium/standalone-chrome:latest '{"browserName": "chrome"}' \
  -K selenium/standalone-firefox:latest '{"browserName": "firefox"}' \
  -K selenium/standalone-edge:latest '{"browserName": "MicrosoftEdge"}'
```

For complex Job definitions, store the full Job YAML in a ConfigMap and reference it with the `configmap:` prefix:

```bash
java -jar selenium-server.jar \
  --ext $(coursier fetch -p org.seleniumhq.selenium:selenium-node-kubernetes:latest.release) \
  node \
  -K configmap:my-chrome-template '{"browserName": "chrome"}'
```

Noted: The ConfigMap should be deployed to cluster and must contain a key named `template` whose value is a valid K8s Job YAML. The Job must have a container named `browser` with an image. Cross-namespace references use `namespace/configmap-name`.

Or pass a TOML config file (equivalent to the ConfigMap approach above):

```bash
java -jar selenium-server.jar \
  --ext $(coursier fetch -p org.seleniumhq.selenium:selenium-node-kubernetes:latest.release) \
  node \
  --config /opt/selenium/kubernetes.toml
```

Where `kubernetes.toml` contains the `[kubernetes]` section with `configs`, resource requests/limits, and any other tuning — matching exactly the ConfigMap content shown in the deployment section above.

---

## Summary

Selenium Grid 4.41.0 is a release with real depth. The major themes:

| Feature | PR(s) | Impact                                                                                   |
|---|---|------------------------------------------------------------------------------------------|
| **Dynamic Grid for Kubernetes** | [selenium#17092](https://github.com/SeleniumHQ/selenium/pull/17092), [docker-selenium#3082](https://github.com/SeleniumHQ/docker-selenium/pull/3082) | On-demand browser Pods natively in K8s clusters                                          |
| **Session Event API** | [selenium#17015](https://github.com/SeleniumHQ/selenium/pull/17015) | Client-to-grid event push, ZeroMQ sidecar consumption, all language bindings             |
| **Event-driven video recording** | [docker-selenium#3070](https://github.com/SeleniumHQ/docker-selenium/pull/3070), [docker-selenium#3084](https://github.com/SeleniumHQ/docker-selenium/pull/3084) | Precise start/stop by default; failure-only upload with `SE_UPLOAD_FAILURE_SESSION_ONLY` |
| **Traefik default ingress** | [docker-selenium#3083](https://github.com/SeleniumHQ/docker-selenium/pull/3083) | Ahead of Ingress NGINX EOL; better WebSocket support                                     |
| **Distributor reliability** | [selenium#17104](https://github.com/SeleniumHQ/selenium/pull/17104), [selenium#17106](https://github.com/SeleniumHQ/selenium/pull/17106), [selenium#17022](https://github.com/SeleniumHQ/selenium/pull/17022), [selenium#17109](https://github.com/SeleniumHQ/selenium/pull/17109) | Thread exhaustion, WebSocket leaks, deadlock, retry fixed                                |
| **Browser version matrix** | [docker-selenium#3085](https://github.com/SeleniumHQ/docker-selenium/pull/3085) | 167+ image variants across Chrome, Edge, Firefox; interactive Fluxbox browser menu       |
| **Structured logs** | [docker-selenium#3087](https://github.com/SeleniumHQ/docker-selenium/pull/3087) | `SE_PLAIN_LOGS` toggle for JSON-only log pipelines                                       |

We are proud of this release and excited about what it unlocks — particularly the Kubernetes Dynamic Grid, which has been one of the most features for advanced users. The Session Event API is equally exciting as a foundation: we expect to see community-built integrations emerge quickly.

As always, feedback and bug reports are welcome on [selenium/issues](https://github.com/SeleniumHQ/selenium/issues) and [docker-selenium/issues](https://github.com/SeleniumHQ/docker-selenium/issues).

Happy testing!

---

## References

### Selenium Core (selenium-4.41.0)

| PR | Description |
|---|---|
| [#17015](https://github.com/SeleniumHQ/selenium/pull/17015) | Add session event API for server-side event bus integration |
| [#17022](https://github.com/SeleniumHQ/selenium/pull/17022) | Preventing potential deadlock in Distributor |
| [#17060](https://github.com/SeleniumHQ/selenium/pull/17060) | Revert default standalone config |
| [#17072](https://github.com/SeleniumHQ/selenium/pull/17072) | Dynamic Grid standalone support passing basic auth credential |
| [#17092](https://github.com/SeleniumHQ/selenium/pull/17092) | Support Dynamic Grid in Kubernetes cluster |
| [#17097](https://github.com/SeleniumHQ/selenium/pull/17097) | Restore stereotype capabilities merging in RelaySessionFactory |
| [#17104](https://github.com/SeleniumHQ/selenium/pull/17104) | Fix Distributor thread exhaustion in node health-check cycle |
| [#17106](https://github.com/SeleniumHQ/selenium/pull/17106) | Fix WebSocket connection counter leaks in ProxyNodeWebsockets |
| [#17109](https://github.com/SeleniumHQ/selenium/pull/17109) | Distributor retry session when RemoteNode executor shutting down |

### Docker Selenium (4.41.0-20260222)

| PR | Description |
|---|---|
| [#3070](https://github.com/SeleniumHQ/docker-selenium/pull/3070) | Video recorder/uploader listen on session events |
| [#3082](https://github.com/SeleniumHQ/docker-selenium/pull/3082) | Images for Dynamic Grid in Kubernetes |
| [#3083](https://github.com/SeleniumHQ/docker-selenium/pull/3083) | Replace Ingress NGINX with Traefik for default ingress controller |
| [#3084](https://github.com/SeleniumHQ/docker-selenium/pull/3084) | Enable SE_VIDEO_EVENT_DRIVEN by default |
| [#3085](https://github.com/SeleniumHQ/docker-selenium/pull/3085) | Add browser selection to Fluxbox menu and lightweight terminal |
| [#3086](https://github.com/SeleniumHQ/docker-selenium/pull/3086) | Missing tolerations for monitoring exporter deployment |
| [#3087](https://github.com/SeleniumHQ/docker-selenium/pull/3087) | Add config to disable plain logs while enabling structured logs |
| [#3088](https://github.com/SeleniumHQ/docker-selenium/pull/3088) | Unified configs for Dynamic Grid Docker and Kubernetes |
| [#3089](https://github.com/SeleniumHQ/docker-selenium/pull/3089) | Video event-driven service deal with standalone status endpoint has basic auth |

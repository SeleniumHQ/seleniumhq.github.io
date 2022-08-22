---
title: "Scaling a Kubernetes Selenium Grid with KEDA"
linkTitle: "Scaling a Kubernetes Selenium Grid with KEDA"
date: 2022-08-19
tags: ["grid", "selenium", "keda", "kubernetes"]
categories: ["general"]
author: Brandon Wolfe ([@Wolfe1](https://github.com/Wolfe1))
description: >
  Scaling Selenium Grid in a Kubernetes cluster with the help of KEDA
---

## The Issue

If you have any experience with Selenium Grid and Kubernetes you will probably
run into an issue with scaling. Kubernetes (K8S) works wonders for scaling up and 
down applications based on their CPU and Memory usage, but it is not so 
straightforward when it comes down to applications like Selenium Grid.

The issue is described quite well in [this blog post](https://sahajamit.medium.com/spinning-up-your-own-auto-scalable-selenium-grid-in-kubernetes-part-2-15b11f228ed8).
But in short, the Horizontal Pod AutoScaler (HPA) that is built into 
Kubernetes checks (by default) for resource consumption to determine 
if a deployment needs to be scaled up or down. This becomes an issue 
for Selenium Grid for a couple reasons:

1. The browser pods use a variable amount of resources depending on 
the demand of the current test. This means that all your browser pods 
may be in use but there isn't enough CPU usage for the HPA to decide 
that a scale-up is needed, leaving tests waiting in the queue unnecessarily.
2. When Kubernetes decides to scale down a deployment it does so 
(for the most part) at random. You could have 10 tests running on 
20 pods and need to scale down. More than likely at least one of 
the pods asked to terminate will still have a test running, resulting 
in connection failures.

## How KEDA Helps

[KEDA](https://keda.sh/) is a **free and open-source** Kubernetes 
event-driven autoscaling solution that extends the feature set of 
K8S' HPA. This is done via plugins written by the community that 
feed KEDA's metrics server with the information it needs to scale 
specific deployments up and down.

Specifically for Selenium Grid, we have a [plugin](https://keda.sh/docs/latest/scalers/selenium-grid-scaler/) 
that will tie into our grid to get the information it needs. Example of the used trigger:

```yml
triggers:
  - type: selenium-grid
    metadata:
      url: 'http://selenium-grid-url-or-ip:4444/graphql'
      browserName: 'chrome'
```

All of this gets saved as a Scaled-Object like so:

```yml
apiVersion: keda.sh/v1alpha1
kind: ScaledObject
metadata:
  name: selenium-chrome-scaledobject
  namespace: <namespace of your browser pods>
  labels:
    deploymentName: selenium-chrome-node-deployment
spec:
  minReplicaCount: 0
  maxReplicaCount: 80
  scaleTargetRef:
    name: selenium-chrome-node-deployment
  triggers:
    - type: selenium-grid
      metadata:
        url: 'https://selenium-grid-url-or-ip:4444/graphql'
        browserName: 'chrome'
```

As an added bonus KEDA allows us to scale our deployments down to 
0 when there is no use, something the normal out-of-the-box HPA 
does not allow.

Check more details at [the documentation for Scaled-Object in KEDA](https://keda.sh/docs/latest/concepts/scaling-deployments/).

A full example of how to implement this is further down in the article 
but KEDA solves one of our two issues. Now we can properly scale up and 
down based on the actual load on the Selenium Grid. Unfortunately scaling 
down still results in the likely possibility that a pod is still running 
a test and is told to terminate before it can finish.

## Using PreStop and Drain

To combat this we are going to use a combination of K8s 
[PreStop](https://kubernetes.io/docs/concepts/containers/container-lifecycle-hooks/#container-hooks) 
and Selenium Grid's [Drain](https://www.selenium.dev/documentation/grid/advanced_features/endpoints/#drain-node) 
functionality.

- `PreStop` allows us to set a command or chain of commands that is run to completion before the container is told to stop.
- Drain tells the selenium browser pod to finish its current test and then shut down.

Together these look like so in our browser pod yaml:

```yml
spec:
  template:
    spec:
      terminationGracePeriodSeconds: 3600
      ...
      ...
      containers:
        lifecycle:
          preStop:
            exec:
              command: ["/bin/sh", "-c", "curl --request POST 'localhost:5555/se/grid/node/drain' --header 'X-REGISTRATION-SECRET;'; tail --pid=$(pgrep -f '[n]ode --bind-host false --config /opt/selenium/config.toml') -f /dev/null; sleep 30s"]
```

#### Breaking this down

- `terminationGracePeriodSeconds` is set to however long you wish to give your 
pods to gracefully terminate before being forced. In this case I give the pods 
60 minutes to finish their test when asked to terminate. If you are also scaling 
your cluster nodes as a part of this you may need to increase the termination 
grace period for your cluster nodes as well.
- When the pod is told to stop, the `PreStop` command is ran first.
- We curl the `localhost` of our pod to tell it to drain. The pod will no 
longer accept new session requests and will finish its current test. More
 information on this [can be found in the Selenium Grid documentation](https://www.selenium.dev/documentation/grid/advanced_features/endpoints/#drain).
- We then tail the internal node process that will continue to run until the node has been drained.
- After this we give the pod 30 seconds to finish anything else before giving the full termination command.

And with that our application can now safely scale down our selenium browser deployments!

## From Start to Finish

### Install KEDA

- You need to use version 2.8.0 or later, you can find the latest version number at the 
[Selenium Grid Scaler docs](https://keda.sh/docs/latest/scalers/selenium-grid-scaler/).
- `kubectl apply -f https://github.com/kedacore/keda/releases/download/<Version_Number_Here>/keda-<Version_Number_Here>.yaml`

### Create and apply your scaled object(s)

As described earlier your [scaled object](https://keda.sh/docs/latest/scalers/selenium-grid-scaler/) will look like so:

```yml
apiVersion: keda.sh/v1alpha1
kind: ScaledObject
metadata:
  name: selenium-chrome-scaledobject
  namespace: <namespace of your browser pods>
  labels:
    deploymentName: selenium-chrome-node-deployment
spec:
  minReplicaCount: 0
  maxReplicaCount: 80
  scaleTargetRef:
    name: selenium-chrome-node-deployment
  triggers:
    - type: selenium-grid
      metadata:
        url: 'https://selenium-grid-url-or-ip:4444/graphql'
        browserName: 'chrome'
```
You will need one of these for every browser you wish to scale.

Things to edit:

1. `namespace` should be the namespace that your selenium browser pods exist in
2. `deploymentName` are the name of your browser deployment
3. `name` (within spec) is also the name of your browser deployment
4. `url` is the url of your selenium grid
5. `browserName` is the name of the browser you are using
6. `minReplicaCount` and `maxReplicaCount` are the min and maximum pod count you want to have

If you plan to scale with Edge you will need at least version `2.8.0` of KEDA and will 
need to include `sessionBrowserName` as well in the trigger metadata:

```yml
triggers:
    - type: selenium-grid
      metadata:
        url: 'https://selenium-grid-url-or-ip:4444/graphql'
        browserName: 'MicrosoftEdge'
        sessionBrowserName: 'msedge'
```

This is due to a name change between the Edge sessions in the queue and the active 
sessions and is [addressed through this pull request](https://github.com/kedacore/keda/pull/3062).

Once you have that ready just save it as a yaml file and apply with:
- `kubectl apply -f ./<scaled-object-file-name>.yaml --namespace=<browser_namespace>`

### Add PreStop commands to your browser pods

1. Set your `terminationGracePeriodSeconds` of your deployment to whatever the maximum 
time you wish to give the pods in order to terminate gracefully. Again you may need to 
also increase the grace period for your nodepool as well which will vary depending on 
your K8s provider.
2. Add the `PreStop` command to the container lifecycle spec:

```yml
spec:
  template:
    spec:
      terminationGracePeriodSeconds: 3600
      ...
      ...
      containers:
        lifecycle:
          preStop:
            exec:
              command: ["/bin/sh", "-c", "curl --request POST 'localhost:5555/se/grid/node/drain' --header 'X-REGISTRATION-SECRET;'; tail --pid=$(pgrep -f '[n]ode --bind-host false --config /opt/selenium/config.toml') -f /dev/null; sleep 30s"]
```

That is it, your Selenium Grid pods should now scale up and down properly without any lost sessions!
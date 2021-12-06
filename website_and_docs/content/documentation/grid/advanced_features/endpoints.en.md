---
title: "Grid endpoints"
linkTitle: "Endpoints"
weight: 3
aliases: [
"/documentation/en/grid/grid_4/grid_endpoints/",
"/documentation/grid/advanced_features/grid_endpoints/"
]
---

## Grid 

### Grid Status

Grid status provides the current state of the Grid. It consists of details about every registered Node.
For every Node, the status includes information regarding Node availability, sessions, and slots. 

```shell
cURL GET 'http://localhost:4444/status'
```

In the Standalone mode, the Grid URL is the Standalone server address.

In the Hub-Node mode, the Grid URL is the Hub server address. 

In the fully distributed mode, the Grid URL is the Router server address.

Default URL for all the above modes is http://localhost:4444.

## Distributor

### Remove Node

To remove the Node from the Grid, use the cURL command enlisted below.
It does not stop any ongoing session running on that Node.
The Node continues running as it is unless explicitly killed. 
The Distributor is no longer aware of the Node and hence any matching new session request
will not be forwarded to that Node.

In the Standalone mode, the Distributor URL is the Standalone server address. 

In the Hub-Node mode, the Distributor URL is the Hub server address.  
```shell
cURL --request DELETE 'http://localhost:4444/se/grid/distributor/node/<node-id>' --header 'X-REGISTRATION-SECRET: <secret> '
```
In the fully distributed mode, the URL is the Distributor server address. 
```shell
cURL --request DELETE 'http://localhost:5553/se/grid/distributor/node/<node-id>' --header 'X-REGISTRATION-SECRET: <secret>'
```
If no registration secret has been configured while setting up the Grid, then use 
```shell
cURL --request DELETE 'http://<Distributor-URL>/se/grid/distributor/node/<node-id>' --header 'X-REGISTRATION-SECRET;'
```

### Drain Node

Node drain command is for graceful node shutdown.
Draining a Node stops the Node after all the ongoing sessions are complete.
However, it does not accept any new session requests.

In the Standalone mode, the Distributor URL is the Standalone server address. 

In the Hub-Node mode, the Distributor URL is the Hub server address.  
```shell
cURL --request POST 'http://localhost:4444/se/grid/distributor/node/<node-id>/drain' --header 'X-REGISTRATION-SECRET: <secret> '
```
In the fully distributed mode, the URL is the Distributor server address. 
```shell
cURL --request POST 'http://localhost:5553/se/grid/distributor/node/<node-id>/drain' --header 'X-REGISTRATION-SECRET: <secret>'
```
If no registration secret has been configured while setting up the Grid, then use 
```shell
cURL --request POST 'http://<Distributor-URL>/se/grid/distributor/node/<node-id>/drain' --header 'X-REGISTRATION-SECRET;'
```

## Node

The endpoints in this section are applicable for Hub-Node mode and fully distributed Grid mode where the
Node runs independently. 
The default Node URL is http://localhost:5555 in case of one Node. 
In case of multiple Nodes, use [Grid status](#grid-status) to get all Node details and locate the Node address.

### Status 

The Node status is essentially a health-check for the Node.
Distributor pings the node status are regular intervals and updates the Grid Model accordingly.
The status includes information regarding availability, sessions, and slots. 

```shell
cURL --request GET 'http://localhost:5555/status'
```

### Drain

Distributor passes the [drain](#drain-node) command to the appropriate node identified by the node-id.
To drain the Node directly, use the cuRL command enlisted below.
Both endpoints are valid and produce the same result. Drain finishes the ongoing sessions before stopping the Node.

```shell
cURL --request POST 'http://localhost:5555/se/grid/node/drain' --header 'X-REGISTRATION-SECRET: <secret>'
```
If no registration secret has been configured while setting up the Grid, then use 
```shell
cURL --request POST 'http://<node-URL>/se/grid/node/drain' --header 'X-REGISTRATION-SECRET;'
```

### Check session owner

To check if a session belongs to a Node, use the cURL command enlisted below. 

```shell
cURL --request GET 'http://localhost:5555/se/grid/node/owner/<session-id>' --header 'X-REGISTRATION-SECRET: <secret>'
```
If no registration secret has been configured while setting up the Grid, then use 
```shell
cURL --request GET 'http://<node-URL>/se/grid/node/owner/<session-id>' --header 'X-REGISTRATION-SECRET;'
```

It will return true if the session belongs to the Node else it will return false.

### Delete session

Deleting the session terminates the WebDriver session, quits the driver and removes it from the active sessions map.
Any request using the removed session-id or reusing the driver instance will throw an error.

```shell
cURL --request DELETE 'http://localhost:5555/se/grid/node/session/<session-id>' --header 'X-REGISTRATION-SECRET: <secret>'
```
If no registration secret has been configured while setting up the Grid, then use 
```shell
cURL --request DELETE 'http://<node-URL>/se/grid/node/session/<session-id>' --header 'X-REGISTRATION-SECRET;'
```

## New Session Queue

### Clear New Session Queue

New Session Request Queue holds the new session requests. 
To clear the queue, use the cURL command enlisted below. 
Clearing the queue rejects all the requests in the queue. For each such request, the server returns an error response to the respective client.
The result of the clear command is the total number of deleted requests.

In the Standalone mode, the Queue URL is the Standalone server address. 

In the Hub-Node mode, the Queue URL is the Hub server address.

```shell
cURL --request DELETE 'http://localhost:4444/se/grid/newsessionqueue/queue' --header 'X-REGISTRATION-SECRET: <secret>'
```

In the fully distributed mode, the Queue URL is New Session Queue server address.
```shell
cURL --request DELETE 'http://localhost:5559/se/grid/newsessionqueue/queue' --header 'X-REGISTRATION-SECRET: <secret>'
```

If no registration secret has been configured while setting up the Grid, then use 
```shell
cURL --request DELETE 'http://<URL>/se/grid/newsessionqueue/queue' --header 'X-REGISTRATION-SECRET;'
```

### Get New Session Queue Requests

New Session Request Queue holds the new session requests. 
To get the current requests in the queue, use the cURL command enlisted below. 
The response returns the total number of requests in the queue and the request payloads.

In the Standalone mode, the Queue URL is the Standalone server address. 

In the Hub-Node mode, the Queue URL is the Hub server address.

```shell
cURL --request GET 'http://localhost:4444/se/grid/newsessionqueue/queue'
```

In the fully distributed mode, the Queue URL is New Session Queue server address.
```shell
cURL --request GET 'http://localhost:5559/se/grid/newsessionqueue/queue'

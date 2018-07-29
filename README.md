# Distributed Cache
Builds a distributed Key-Value (KV) store.
Key-Value paris can be added/retrieved using simple Get/Set RESTful APIs. It uses in-memory HSQL database to store the cache.
The same project can be used to spawn two separate processes. Once started, it will always try to communicate with the other to sync all the available cache from other process.
Once a set key-value pair API is invoked, process will update cache in it's own memory, and then it will send the same key-value to other process asynchronously to update the cache in other process.
That way, the two processes communicate with each other at runtime, and key set at one process can be retrieved from other process.

## Compile
Run following command to compile the code

```mvn clean install```

## Run
While starting the process, mention the server and client ports. Server port will be used by the tomcat to start first app, and client port is used to communicate with the other app.

<br><B>Note: </B>The ports should be reversed in the two commands.

### Start process 1
```mvn spring-boot:run -Dserver.port=4455 -Dclient.port=4466```

### Start process 2
```mvn spring-boot:run -Dserver.port=4466 -Dclient.port=4455```

## Test
### Set Key-Value
Examples:

```curl -H "Content-type: text/plain" -XPOST http://localhost:4455/set/key -d "value"```

```curl -H "Content-type: text/plain" -XPOST http://localhost:4466/set/key -d "value"```

### Get Key-Value
Examples:

```curl http://localhost:4455/get/key```

```curl http://localhost:4466/get/key```


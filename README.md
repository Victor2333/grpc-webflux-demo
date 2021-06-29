# Spring WebFlux Grpc Demo
> Spring boot with WebFlux and Grpc

| module | description |
|:-------|:------------|
|[reactor-web-flux](https://github.com/salesforce/reactive-grpc/tree/master/reactor)|Reactor-gRPC is a set of gRPC bindings for reactive programming with Reactor.|
|[springboot-integration](https://yidongnan.github.io/grpc-spring-boot-starter/zh-CN/server/getting-started.html)|Spring Boot starter module for gRPC framework.|

## How to use
```bash
mvn clean package
java -jar provider/target/provider-0.0.1-SNAPSHOT.jar
java -jar consumer/target/consumer-0.0.1-SNAPSHOT.jar
```
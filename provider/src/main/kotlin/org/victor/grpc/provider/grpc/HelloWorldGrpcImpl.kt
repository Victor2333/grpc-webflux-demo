package org.victor.grpc.provider.grpc

import io.grpc.examples.helloworld.HelloReply
import io.grpc.examples.helloworld.HelloRequest
import io.grpc.examples.helloworld.ReactorGreeterGrpc
import net.devh.boot.grpc.server.service.GrpcService
import reactor.core.publisher.Mono

@GrpcService
class HelloWorldService : ReactorGreeterGrpc.GreeterImplBase() {
    override fun sayHello(request: Mono<HelloRequest>?): Mono<HelloReply> {
        return request?.map {
            HelloReply.newBuilder().setMessage(it.name).build()
        } ?: Mono.empty()
    }
}
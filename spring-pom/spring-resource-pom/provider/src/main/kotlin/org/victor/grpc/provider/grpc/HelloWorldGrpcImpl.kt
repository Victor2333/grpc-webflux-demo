package org.victor.grpc.provider.grpc

import org.lognet.springboot.grpc.GRpcService
import org.slf4j.LoggerFactory
import org.victor.grpc.api.hello.HelloRequest
import org.victor.grpc.api.hello.HelloResponse
import org.victor.grpc.api.hello.ReactorGreeterGrpc
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toFlux

@GRpcService
class HelloWorldService : ReactorGreeterGrpc.GreeterImplBase() {

    private val logger = LoggerFactory.getLogger(HelloWorldService::class.java)

    override fun greet(request: Mono<HelloRequest>?): Mono<HelloResponse> {
        logger.info("greet")
        return request?.map { HelloResponse.newBuilder().setMessage("Hi, ${it.name}").build() } ?: Mono.empty()
    }

    override fun multiGreet(request: Mono<HelloRequest>?): Flux<HelloResponse> {
        logger.info("multiple greet")
        return request
            ?.map { HelloResponse.newBuilder().setMessage("Hi, ${it.name}").build() }
            ?.toFlux()
            ?: Flux.empty()
    }

    override fun streamGreet(request: Flux<HelloRequest>?): Flux<HelloResponse> {
        logger.info("stream greet")
        return request
            ?.map { HelloResponse.newBuilder().setMessage("Hi, ${it.name}").build() }
            ?: Flux.empty()
    }

}
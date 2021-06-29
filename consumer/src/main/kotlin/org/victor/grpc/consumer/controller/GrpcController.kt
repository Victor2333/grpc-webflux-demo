package org.victor.grpc.consumer.controller

import com.google.common.collect.ImmutableMap
import net.devh.boot.grpc.client.inject.GrpcClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.victor.grpc.api.hello.HelloRequest
import org.victor.grpc.api.hello.ReactorGreeterGrpc
import reactor.core.publisher.Flux

@RestController
@RequestMapping("/grpc")
class GrpcController() {

    @GrpcClient("hello")
    private lateinit var grpcClient: ReactorGreeterGrpc.ReactorGreeterStub

    @GetMapping("/hello")
    fun hello(): Flux<Map<String, String>> {
        return Flux.just(
            HelloRequest.newBuilder().setName("a").build(),
            HelloRequest.newBuilder().setName("b").build(),
            HelloRequest.newBuilder().setName("c").build()
        ).transform(grpcClient::streamGreet).map { ImmutableMap.of("message", it.message) }
    }
}
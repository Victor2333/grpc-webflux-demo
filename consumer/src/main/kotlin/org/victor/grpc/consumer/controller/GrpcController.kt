package org.victor.grpc.consumer.controller

import com.google.common.collect.ImmutableMap
import com.netflix.discovery.EurekaClient
import io.grpc.ManagedChannelBuilder
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.victor.grpc.api.hello.HelloRequest
import org.victor.grpc.api.hello.ReactorGreeterGrpc
import reactor.core.publisher.Flux

@RestController
@RequestMapping("/grpc")
class GrpcController(val grpcClient: () -> ReactorGreeterGrpc.ReactorGreeterStub) {

    @GetMapping("/hello")
    fun hello(): Flux<Map<String, String>> {
        return Flux.just(
            HelloRequest.newBuilder().setName("a").build(),
            HelloRequest.newBuilder().setName("b").build(),
            HelloRequest.newBuilder().setName("c").build()
        ).transform { grpcClient.invoke().streamGreet(it) }.map { ImmutableMap.of("message", it.message) }
    }
}

@Configuration
class GRpcClient(val eurekaClient: EurekaClient) {
    @Value("\${grpc.provider.name}")
    lateinit var serviceName: String;

    @Bean
    fun grpcClient(): () -> ReactorGreeterGrpc.ReactorGreeterStub {
        return {
            val instanceInfo = eurekaClient.getNextServerFromEureka(serviceName, false)
            val managedChannel = ManagedChannelBuilder
                .forAddress(instanceInfo.ipAddr, instanceInfo.port)
                .usePlaintext()
                .build()
            ReactorGreeterGrpc
                .newReactorStub(managedChannel)
        }
    }
}
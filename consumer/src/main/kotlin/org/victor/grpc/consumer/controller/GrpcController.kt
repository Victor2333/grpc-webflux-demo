package org.victor.grpc.consumer.controller

import io.grpc.examples.helloworld.GreeterGrpc
import io.grpc.examples.helloworld.HelloReply
import io.grpc.examples.helloworld.HelloRequest
import io.grpc.stub.StreamObserver
import net.devh.boot.grpc.client.inject.GrpcClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/grpc")
class GrpcController() {

    @GrpcClient("hello")
    private lateinit var grpcClient: GreeterGrpc.GreeterStub

    @GetMapping("/hello")
    fun hello() {
        val responseObserver = object : StreamObserver<HelloReply> {
            override fun onNext(helloReply: HelloReply?) {
                println(helloReply)
            }

            override fun onError(error: Throwable?) {
                println(error?.message)
            }

            override fun onCompleted() {
                println("rpc call completed")
            }

        }
        grpcClient.sayHello(HelloRequest.newBuilder().setName("test").build(), responseObserver)
    }
}
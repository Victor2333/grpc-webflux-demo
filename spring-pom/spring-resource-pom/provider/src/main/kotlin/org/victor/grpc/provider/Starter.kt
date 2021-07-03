package org.victor.grpc.provider

import org.springframework.boot.WebApplicationType
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder

@SpringBootApplication
class Starter

fun main(args: Array<String>) {
    SpringApplicationBuilder()
        .web(WebApplicationType.NONE)
        .sources(Starter::class.java)
        .build(*args)
        .run()
}

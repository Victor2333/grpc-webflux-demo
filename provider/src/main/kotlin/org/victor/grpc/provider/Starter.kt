package org.victor.grpc.provider

import org.springframework.boot.WebApplicationType
import org.springframework.boot.builder.SpringApplicationBuilder
import org.victor.anno.VictorSpringBootStarter

@VictorSpringBootStarter
class Starter

fun main(args: Array<String>) {
    SpringApplicationBuilder()
        .web(WebApplicationType.NONE)
        .sources(Starter::class.java)
        .build(*args)
        .run()
}

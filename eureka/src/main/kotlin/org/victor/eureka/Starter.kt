package org.victor.eureka

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer
import org.springframework.context.annotation.Configuration

@Configuration
@EnableAutoConfiguration
@EnableEurekaServer
class Starter

fun main(args: Array<String>) {
    runApplication<Starter>(*args)
}
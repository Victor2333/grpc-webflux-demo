package org.victor.config

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.cloud.config.server.EnableConfigServer
import org.springframework.context.annotation.Configuration

@Configuration
@EnableAutoConfiguration
@EnableConfigServer
class Starter

fun main(args: Array<String>) {
    runApplication<Starter>(*args)
}
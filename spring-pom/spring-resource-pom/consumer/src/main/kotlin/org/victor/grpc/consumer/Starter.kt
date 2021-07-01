package org.victor.grpc.consumer

import org.springframework.boot.runApplication
import org.victor.anno.VictorSpringBootStarter

@VictorSpringBootStarter
class Starter

fun main(args: Array<String>) {
    runApplication<Starter>(*args)
}
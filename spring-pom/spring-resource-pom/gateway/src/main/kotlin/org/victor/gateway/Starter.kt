package org.victor.gateway

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.gateway.route.RouteLocator
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import springfox.documentation.oas.annotations.EnableOpenApi
import springfox.documentation.swagger.web.SwaggerResource
import springfox.documentation.swagger.web.SwaggerResourcesProvider

@EnableOpenApi
@SpringBootApplication
class Starter

const val API_ENDPOINT = "/v3/api-docs"

@Primary
@Configuration
class SwaggerResourceConfiguration(val routeLocator: RouteLocator) : SwaggerResourcesProvider {
    @Value("\${gateway.service.prefix:''}")
    lateinit var gatewayPrefix: String

    override fun get(): MutableList<SwaggerResource> {
        val swaggerResourceList = ArrayList<SwaggerResource>()
        routeLocator.routes.subscribe {
            val swaggerResource = SwaggerResource()
            swaggerResource.name = it.id
            swaggerResource.url = "${gatewayPrefix}/${it.uri.host.lowercase()}${API_ENDPOINT}"
            swaggerResource.swaggerVersion = "3.0"
            swaggerResourceList.add(swaggerResource)
        }
        return swaggerResourceList
    }
}

fun main(args: Array<String>) {
    runApplication<Starter>(*args)
}

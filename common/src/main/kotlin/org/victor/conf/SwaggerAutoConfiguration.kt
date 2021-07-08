package org.victor.conf

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.oas.annotations.EnableOpenApi
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import java.net.URI

@Configuration
@ConditionalOnProperty(value = ["eureka.instance.metadata-map.registry-to-gateway"], havingValue = "true")
@EnableOpenApi
class SwaggerAutoConfiguration {

    @Value("\${spring.application.name}")
    lateinit var appName: String

    @Value("\${gateway.service.uri}")
    lateinit var gatewayUri: URI

    @Value("\${gateway.service.prefix}")
    lateinit var gatewayPrefix: String

    @Bean
    @ConditionalOnMissingBean
    fun docket(): Docket? {
        return Docket(DocumentationType.OAS_30)
            .select()
            .apis(RequestHandlerSelectors.any())
            .paths(PathSelectors.any())
            .build()
            .host("${gatewayUri.host}:${gatewayUri.port}")
            .pathMapping("${gatewayPrefix}/${appName}")
    }
}
package org.victor.conf

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.*
import springfox.documentation.oas.annotations.EnableOpenApi
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket

@Configuration
@EnableOpenApi
class SwaggerAutoConfiguration {

    @Value("\${spring.application.name}")
    lateinit var appName: String

    @Value("\${gateway.host}")
    lateinit var gateWay: String

    @Bean
    @ConditionalOnMissingBean
    fun restApi(): Docket? {
        return Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.any())
            .paths(PathSelectors.any())
            .build()
            .host(gateWay)
            .pathMapping("/${appName}")
    }
}
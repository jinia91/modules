package kr.co.external_api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication(
    scanBasePackages = [
        "kr.co.external_api"
    ]
)
@ConfigurationPropertiesScan(
    basePackages = [
        "kr.co.external_api"
    ]
)
class ExternalApiApplication

fun main(args: Array<String>) {
    runApplication<ExternalApiApplication>(*args)
}

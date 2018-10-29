package ru.zgys.sabbathtime.configuration

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate

/**
 * @author U.Goryntsev 28.10.2018.
 */
@Configuration
class RestConfig {
    @Bean
    fun restTemplate(): RestTemplate = RestTemplate()


    @Bean
    fun objectMapper() = ObjectMapper().registerKotlinModule()

    @Bean
    fun xmlMapper() = XmlMapper()
}
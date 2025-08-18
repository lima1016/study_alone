package com.lima.study_alone.inflearn.springai.config

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.ai.openai.api.OpenAiApi
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class OpenAiConfig {
    private val logger = KotlinLogging.logger{}

    @Value("\${openai.api.key}")
    private lateinit var apiKey: String

    @Bean
    open fun openAiApi(): OpenAiApi {
        logger.debug {"OpenAi API 클라이언트 초기화"}
        return OpenAiApi.builder()
            .apiKey(apiKey)
            .build()
    }
}
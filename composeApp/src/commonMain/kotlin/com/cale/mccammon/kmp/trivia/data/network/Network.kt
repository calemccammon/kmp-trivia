package com.cale.mccammon.kmp.trivia.data.network

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class Network {
    val httpClient = HttpClient {
        install(Logging) {
            logger = Logger.SIMPLE
            level = LogLevel.ALL
        }
        install(ContentNegotiation) {
            json(
                Json {
                    encodeDefaults = true
                    isLenient = true
                    coerceInputValues = true
                    ignoreUnknownKeys = true
                }
            )
        }
    }

    /**
     * Perform a generic GET call
     */
    suspend inline fun <reified T> get(
        endpoint: String,
        headers: Map<String, String>,
        queries: Map<String, String>
    ): T {
        return httpClient.get(endpoint) {
            headers { headers.forEach { append(it.key, it.value) } }
            url { queries.forEach { parameters.append(it.key, it.value) } }
        }.body()
    }
}
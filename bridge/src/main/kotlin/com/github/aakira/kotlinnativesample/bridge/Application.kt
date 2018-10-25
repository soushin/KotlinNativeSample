package com.github.aakira.kotlinnativesample.bridge

import com.fasterxml.jackson.databind.SerializationFeature
import com.github.aakira.kotlinnativesample.common.BridgeModel
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.CallLogging
import io.ktor.features.ContentNegotiation
import io.ktor.jackson.jackson
import io.ktor.response.respond
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty


fun main() {
    embeddedServer(Netty, 8080) {
        install(CallLogging)
        install(ContentNegotiation) {
            jackson {
                configure(SerializationFeature.INDENT_OUTPUT, true)
            }
        }

        routing {
            get("/hello") {
                call.respond(BridgeModel("ktor"))
            }
        }
    }.start(wait = true)
}
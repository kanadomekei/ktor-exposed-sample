package com.example.routes

import com.example.data.Migration
import io.kotest.core.spec.style.StringSpec
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.jackson.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.testing.*

internal class UserRouteTest : StringSpec({
    beforeSpec { Migration() }
    "test" {
        testApplication {
            install(ContentNegotiation) {
                jackson {  }
            }
            val created = client.post("/user") {
                install(ContentNegotiation) {
                    jackson {  }
                }
                contentType(ContentType.Application.Json)
                setBody(CreateUserRequest("user"))
            }
        }
    }
})
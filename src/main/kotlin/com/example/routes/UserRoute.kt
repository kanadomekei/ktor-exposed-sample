package com.example.routes

import com.example.domain.service.UserService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.userRoute() {
    route("/user") {
        post {
            val request = call.receive<CreateUserRequest>()
            val id = UserService.create(request.name)
            call.respond(mapOf("id" to id))
        }
        get("/{id}") {
            val id = call.parameters["id"]?.let { it.toInt() } ?: run {
                return@get call.respond(HttpStatusCode.BadRequest, "IDが指定されていません")
            }
            val user = UserService.find(id) ?: run {
                return@get call.respond(HttpStatusCode.NotFound, "ユーザーが存在しません id: $id")
            }
            call.respond(user)
        }
    }
}

data class CreateUserRequest(val name: String)
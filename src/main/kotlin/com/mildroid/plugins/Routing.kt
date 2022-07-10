package com.mildroid.plugins

import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.routing.*

fun Application.configureRouting() {

/*
    routing {
        get("/") {
            call.respondText("Hello World!")
        }
        // Static plugin. Try to access `/static/index.html`
        static("/static") {
            resources("static")
        }
    }
*/

    routing {
        static("/") {
            resources("static")
            staticBasePackage = "static"
            defaultResource("index.html")
        }

    }

}
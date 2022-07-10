package com.mildroid.plugins

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.html.*
import io.ktor.server.http.content.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.css.*
import kotlinx.html.*


fun Application.configureDsl() {

    routing {
        static("/") {
            resources("static")
        }

        get("/dsl") {
            val name = "Milad"

            call.respondHtml(HttpStatusCode.OK) {
                head {
                    link(rel = "stylesheet", href = "/styles.css", type = "text/css")

                    title {
                        +"mildroid"
                    }
                }
                body {
                    h1 {
                        +"Hello from $name!"
                    }
                }

            }
        }

        get("/styles.css") {
            call.respondCss {
                body {
                    color = Color.white
                    backgroundImage = Image("url('/assets/img/bg.svg')")
                    backgroundSize = "cover"

                    margin(32.px)
                    textAlign = TextAlign.center
                }
                rule("h1.page-title") {
                    color = Color.white
                }
            }
        }

        get("/html-dsl") {
            call.respondHtml {
                head {
                    link(rel = "stylesheet", href = "/styles.css", type = "text/css")
                }
                body {
                    h1(classes = "page-title") {
                        +"Hello from Ktor!"
                    }
                }
            }
        }
    }
}

suspend inline fun ApplicationCall.respondCss(builder: CssBuilder.() -> Unit) {
    this.respondText(CssBuilder().apply(builder).toString(), ContentType.Text.CSS)
}
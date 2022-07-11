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
                    backgroundImage = Image("url(\"${backgroundImages().random()}\")")
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

private fun backgroundImages() = listOf(
    "https://drive.google.com/uc?id=1Sd9R4qbVuad_Ok6eGALpXiW8TdojbUQj&export=download",
    "https://drive.google.com/uc?id=1sV2FUN75E7hi0EKd5KZ_-QYOuF2MNrDP&export=download",
    "https://drive.google.com/uc?id=1hkyvfrw2_u2BXRjk50aol6Mt3VxUr-oi&export=download",
    "https://drive.google.com/uc?id=1dHT_mhh7iAhAplWf1bMQQHiR97mCgfp9&export=download"
)
suspend inline fun ApplicationCall.respondCss(builder: CssBuilder.() -> Unit) {
    this.respondText(CssBuilder().apply(builder).toString(), ContentType.Text.CSS)
}
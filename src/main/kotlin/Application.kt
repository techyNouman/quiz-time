package com.example

import com.example.domain.model.QuizQuestion
import com.example.presentation.config.configureLogging
import com.example.presentation.config.configureRouting
import com.example.presentation.config.configureSerialization
import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    configureLogging()
    configureSerialization()
    configureRouting()
}



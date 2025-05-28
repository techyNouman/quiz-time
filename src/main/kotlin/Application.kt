package com.example

import com.example.domain.model.QuizQuestion
import com.example.presentation.config.configureRouting
import com.example.presentation.config.configureSerialization
import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    configureSerialization()
    configureRouting()

}

val quizQuestions = mutableListOf<QuizQuestion>()

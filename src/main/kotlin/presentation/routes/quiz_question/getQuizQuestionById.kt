package com.example.presentation.routes.quiz_question

import com.example.quizQuestions
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.getQuizQuestionById() {
    get(path = "/quiz/questions/{questionId}") {
        val id = call.parameters["questionId"]
        val question = quizQuestions.find { it.id == id }
        if (question != null) {
            call.respond(question)
        }
    }
}
package com.example.presentation.routes.quiz_question

import com.example.domain.model.QuizQuestion
import com.example.quizQuestions
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.upsertQuizQuestions() {
    post(path = "/quiz/questions"){
        val question = call.receive<QuizQuestion>()
        quizQuestions.add(question)
        call.respondText("Question added successfully")
    }
}
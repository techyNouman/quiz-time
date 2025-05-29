package com.example.presentation.routes.quiz_question

import com.example.domain.QuizQuestionRepository
import com.example.domain.model.QuizQuestion
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.upsertQuizQuestions(
    repository: QuizQuestionRepository
) {
    post(path = "/quiz/questions"){
        val question = call.receive<QuizQuestion>()
        repository.upsertQuestion(question)
        call.respond(
            message = "Question added successfully",
            status = HttpStatusCode.Created
        )
    }
}
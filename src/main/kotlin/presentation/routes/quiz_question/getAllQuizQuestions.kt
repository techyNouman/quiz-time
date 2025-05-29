package com.example.presentation.routes.quiz_question

import com.example.domain.QuizQuestionRepository
import io.ktor.http.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.getAllQuizQuestions(
    repository: QuizQuestionRepository
) {
    get(path = "/quiz/questions") {
        val topicCode = call.parameters["topicCode"]?.toIntOrNull()
        val limit = call.parameters["limit"]?.toIntOrNull()
        val questions = repository.getAllQuestions(topicCode, limit)
        if (questions.isNotEmpty()) {
            call.respond(
                message = questions,
                status = HttpStatusCode.OK
            )
        } else {
            call.respond(
                message = "No questions found",
                status = HttpStatusCode.NotFound
            )
        }

    }
}
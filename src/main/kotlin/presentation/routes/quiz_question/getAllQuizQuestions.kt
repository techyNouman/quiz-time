package com.example.presentation.routes.quiz_question

import com.example.domain.repository.QuizQuestionRepository
import com.example.domain.util.DataError
import com.example.domain.util.onFailure
import com.example.domain.util.onSuccess
import io.ktor.http.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.getAllQuizQuestions(
    repository: QuizQuestionRepository
) {
    get(path = "/quiz/questions") {
        val topicCode = call.parameters["topicCode"]?.toIntOrNull()
        val limit = call.parameters["limit"]?.toIntOrNull()
        repository.getAllQuestions(topicCode, limit)
            .onSuccess { questions ->
                call.respond(
                    message = questions,
                    status = HttpStatusCode.OK
                )
            }
            .onFailure { error ->
                if (error == DataError.Database) {
                    call.respond(
                        message = "An unknown error occurred",
                        status = HttpStatusCode.InternalServerError
                    )
                } else {
                    call.respond(
                        message = "No questions found",
                        status = HttpStatusCode.NotFound
                    )
                }
            }

    }
}
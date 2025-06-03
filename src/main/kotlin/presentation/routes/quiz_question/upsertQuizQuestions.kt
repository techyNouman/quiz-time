package com.example.presentation.routes.quiz_question

import com.example.domain.repository.QuizQuestionRepository
import com.example.domain.model.QuizQuestion
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.upsertQuizQuestions(
    repository: QuizQuestionRepository
) {
    post(path = "/quiz/questions") {
        val question = call.receive<QuizQuestion>()
        when (repository.upsertQuestion(question)) {
            true -> call.respond(
                message = "Question added successfully",
                status = HttpStatusCode.Created
            )

            false -> call.respond(
                message = "Question updated successfully",
                status = HttpStatusCode.OK
            )

            null -> call.respond(
                message = "Error while adding question",
                status = HttpStatusCode.InternalServerError
            )
        }
    }
}
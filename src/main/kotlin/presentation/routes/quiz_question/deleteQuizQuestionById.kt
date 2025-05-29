package com.example.presentation.routes.quiz_question

import com.example.domain.QuizQuestionRepository
import io.ktor.http.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.deleteQuizQuestionById(
    repository: QuizQuestionRepository
){
    delete(path = "/quiz/questions/{questionId}"){
        val id = call.parameters["questionId"]
        if (id.isNullOrBlank()){
            call.respond(
                message = "Question Id required",
                status = HttpStatusCode.BadRequest
            )
            return@delete
        }
       val isDeleted = repository.deleteQuestionById(id)
        if (isDeleted){
            call.respond(HttpStatusCode.NoContent)
        }else{
            call.respond(
                message = "Question not found",
                status = HttpStatusCode.NotFound
            )
        }


    }
}
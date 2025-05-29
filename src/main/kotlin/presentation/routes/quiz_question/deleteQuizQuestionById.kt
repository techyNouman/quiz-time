package com.example.presentation.routes.quiz_question

import com.example.quizQuestions
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.deleteQuizQuestionById(){
    delete(path = "/quiz/questions/{questionId}"){
        val id = call.parameters["questionId"]
        quizQuestions.removeIf{it.id == id}
        call.respondText("Quiz question deleted successfully")

    }
}
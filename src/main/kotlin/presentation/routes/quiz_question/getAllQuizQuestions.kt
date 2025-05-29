package com.example.presentation.routes.quiz_question

import com.example.quizQuestions
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.getAllQuizQuestions(){
    get(path = "/quiz/questions"){
        val topicCode = call.parameters["topicCode"]?.toIntOrNull()
        val limit = call.parameters["limit"]?.toIntOrNull()
        val filteredQuestions = quizQuestions.filter { it.topicCode == topicCode }.take(limit ?: 1)
        call.respond(filteredQuestions)
    }
}
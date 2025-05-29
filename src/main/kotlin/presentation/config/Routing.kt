package com.example.presentation.config

import com.example.presentation.routes.quiz_question.deleteQuizQuestionById
import com.example.presentation.routes.quiz_question.getAllQuizQuestions
import com.example.presentation.routes.quiz_question.getQuizQuestionById
import com.example.presentation.routes.quiz_question.upsertQuizQuestions
import com.example.presentation.routes.root
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRouting(){
    routing {
       root()
        getAllQuizQuestions()
        upsertQuizQuestions()
        deleteQuizQuestionById()
        getQuizQuestionById()
    }
}
package com.example.domain.repository

import com.example.domain.model.QuizQuestion
import com.example.domain.util.DataError
import com.example.domain.util.Result

interface QuizQuestionRepository {
    suspend fun upsertQuestion(question: QuizQuestion) : Boolean?
    suspend fun getAllQuestions(topicCode: Int?, limit: Int?) : Result<List<QuizQuestion>, DataError>
    suspend fun getQuestionById(id: String) : QuizQuestion?
    suspend fun deleteQuestionById(id: String) : Boolean
}
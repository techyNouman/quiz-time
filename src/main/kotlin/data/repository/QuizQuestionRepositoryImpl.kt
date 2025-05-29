package com.example.data.repository

import com.example.data.database.entity.QuizQuestionEntity
import com.example.data.mapper.toQuizQuestion
import com.example.data.mapper.toQuizQuestionEntity
import com.example.data.util.Constants.QUESTIONS_COLLECTION_NAME
import com.example.domain.QuizQuestionRepository
import com.example.domain.model.QuizQuestion
import com.mongodb.client.model.Filters
import com.mongodb.client.model.Updates
import com.mongodb.kotlin.client.coroutine.MongoDatabase
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList

class QuizQuestionRepositoryImpl(mongoDatabase: MongoDatabase) : QuizQuestionRepository {

    private val quizCollection = mongoDatabase.getCollection<QuizQuestionEntity>(QUESTIONS_COLLECTION_NAME)

    override suspend fun upsertQuestion(question: QuizQuestion) {
        try {
            if (question.id == null) {
                quizCollection.insertOne(question.toQuizQuestionEntity())
            } else {
                val filterQuery = Filters.eq(QuizQuestionEntity::_id.name, question.id)
                val updateQuery = Updates.combine(
                    Updates.set(QuizQuestionEntity::question.name, question.question),
                    Updates.set(QuizQuestionEntity::correctAnswer.name, question.correctAnswer),
                    Updates.set(QuizQuestionEntity::incorrectAnswers.name, question.incorrectAnswers),
                    Updates.set(QuizQuestionEntity::explanation.name, question.explanation),
                    Updates.set(QuizQuestionEntity::topicCode.name, question.topicCode)
                )
                quizCollection.updateOne(filter = filterQuery, update = updateQuery)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    override suspend fun getAllQuestions(topicCode: Int?, limit: Int?): List<QuizQuestion> {
        try {
            val questionLimit = limit?.takeIf { it > 0 } ?: 10
            val filterQuery = topicCode?.let {
                Filters.eq(QuizQuestionEntity::_id.name, it)
            } ?: Filters.empty()

            val questions = quizCollection
                .find(filter = filterQuery)
                .limit(questionLimit)
                .map { it.toQuizQuestion() }
                .toList()
            return questions
        } catch (e: Exception) {
            e.printStackTrace()
            return emptyList()
        }

    }

    override suspend fun getQuestionById(id: String): QuizQuestion? {
        try {
            val filterQuery = Filters.eq(QuizQuestionEntity::_id.name, id)
            val questionEntity = quizCollection.find(filter = filterQuery).firstOrNull()
            return questionEntity?.toQuizQuestion()
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
    }

    override suspend fun deleteQuestionById(id: String): Boolean {
        try {
            val filterQuery = Filters.eq(QuizQuestionEntity::_id.name, id)
            val deletedResult = quizCollection.deleteOne(filter = filterQuery)
            return deletedResult.deletedCount > 0
        } catch (e: Exception) {
            e.printStackTrace()
            return false
        }

    }
}
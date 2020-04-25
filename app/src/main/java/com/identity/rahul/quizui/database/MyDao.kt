package com.identity.rahul.quizui.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.identity.rahul.quizui.models.QuestionData

/**
 * Created by Rahul Lad on 4/20/2020.
 * Copyright (2020) by IIFL
 */

@Dao
interface MyDao {

    @Insert
    suspend fun addQuestionD(user: QuestionData)

    @Insert
    suspend fun addAllQuestion(allQuestions: List<QuestionData>)

    @Query("select * from questions")
    suspend fun getAndroidQuestions(): List<QuestionData>

    @Query("select * from questions where id = :questionID")
    suspend fun getQuestionFromID(questionID : Int): QuestionData

    @Query("select * from questions where category = :category")
    suspend fun getQuestionFromCategory(category : String): List<QuestionData>
}
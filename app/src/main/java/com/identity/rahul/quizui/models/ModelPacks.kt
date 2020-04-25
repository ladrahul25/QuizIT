package com.identity.rahul.quizui.models

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "questions")
data class QuestionData(
        @NonNull
        @PrimaryKey
        val id: Int,
        val question: String,
        val optionOne: String,
        val optionTwo: String,
        val optionThree: String,
        val optionFour: String,
        val answer : Int,
        val category: String)
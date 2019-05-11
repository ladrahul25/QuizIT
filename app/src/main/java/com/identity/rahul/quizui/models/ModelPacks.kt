package com.identity.rahul.quizui.models

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.support.annotation.NonNull

@Entity(tableName = "androidquestions")
data class AndroidQuestionData(
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


@Entity(tableName = "javaquestions")
data class JavaQuestionData(
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
package com.identity.rahul.quizui.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.identity.rahul.quizui.models.QuestionData

@Database(entities = arrayOf(QuestionData::class), version = 1)
abstract class QuestionDatabase: RoomDatabase(){
    abstract fun getDbAccess(): MyDao
}
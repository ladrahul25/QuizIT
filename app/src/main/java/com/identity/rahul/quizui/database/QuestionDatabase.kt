package com.identity.rahul.quizui.database

import android.arch.persistence.room.RoomDatabase

abstract class QuestionDatabase: RoomDatabase(){
    abstract fun myDataAccess(): MyDao
}
package com.identity.rahul.quizui.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.identity.rahul.quizui.models.AndroidQuestionData;

import java.util.List;

@Dao
public interface MyDao {

    @Insert
    void addQuestionD(AndroidQuestionData user);

    @Query("select * from users")
    List<AndroidQuestionData> getUsers();
}

package com.identity.rahul.quizui.database

import android.content.Context
import androidx.room.Room
import com.identity.rahul.quizui.R
import com.identity.rahul.quizui.models.QuestionData
import com.identity.rahul.quizui.utils.Constants.Companion.VALUE_CATEGORY_ANDROID
import com.identity.rahul.quizui.utils.Constants.Companion.VALUE_CATEGORY_JAVA

class DatabaseCreatorUtil(var context: Context) {

    var questionData: ArrayList<QuestionData> = ArrayList()

    val db = Room.databaseBuilder(
            context,
            QuestionDatabase::class.java, "question-data"
    ).build()


    suspend fun getQuestionCount(): Int {
        val list = db.getDbAccess().getAndroidQuestions()
        return list.size
    }

    suspend fun getAllQuestion(): List<QuestionData> {
        val list = db.getDbAccess().getAndroidQuestions()
        return list
    }

    suspend fun getQuestionFromID(questionID: Int): QuestionData {
        val question = db.getDbAccess().getQuestionFromID(questionID)
        return question
    }

    suspend fun getQuestionFromCategory(category: String): List<QuestionData> {
        val list = db.getDbAccess().getQuestionFromCategory(category)
        return list
    }

    suspend fun createQuestionDatabase() {
        questionData.add(QuestionData(
                1,
                context.getString(R.string.question_text_default),
                context.getString(R.string.option_one_default),
                context.getString(R.string.option_two_default),
                context.getString(R.string.option_three_default),
                context.getString(R.string.option_four_default),
                1,
                VALUE_CATEGORY_ANDROID))

        questionData.add(QuestionData(
                2,
                context.getString(R.string.question_text_two),
                context.getString(R.string.option_one_second),
                context.getString(R.string.option_two_second),
                context.getString(R.string.option_three_second),
                context.getString(R.string.option_four_second),
                4,
                VALUE_CATEGORY_ANDROID))
        questionData.add(QuestionData(
                3,
                context.getString(R.string.question_three),
                context.getString(R.string.option_one_third),
                context.getString(R.string.option_two_third),
                context.getString(R.string.option_three_third),
                context.getString(R.string.option_four_third),
                2,
                VALUE_CATEGORY_JAVA))
        questionData.add(QuestionData(
                4,
                context.getString(R.string.question_four),
                context.getString(R.string.option_one_forth),
                context.getString(R.string.option_two_forth),
                context.getString(R.string.option_three_forth),
                context.getString(R.string.option_four_forth),
                2,
                VALUE_CATEGORY_JAVA))
        questionData.add(QuestionData(
                5,
                context.getString(R.string.question_five),
                context.getString(R.string.option_one_fifth),
                context.getString(R.string.option_two_fifth),
                context.getString(R.string.option_three_fifth),
                "",
                4,
                VALUE_CATEGORY_JAVA))

        questionData.add(QuestionData(
                6,
                context.getString(R.string.question_six),
                context.getString(R.string.option_one_six),
                context.getString(R.string.option_two_six),
                context.getString(R.string.option_three_six),
                "",
                2,
                VALUE_CATEGORY_JAVA))
        questionData.add(QuestionData(
                7,
                context.getString(R.string.question_seven),
                context.getString(R.string.option_one_seven),
                context.getString(R.string.option_two_seven),
                context.getString(R.string.option_three_seven),
                context.getString(R.string.option_four_seven),
                1,
                VALUE_CATEGORY_JAVA))
        questionData.add(QuestionData(
                8,
                context.getString(R.string.question_eight),
                context.getString(R.string.option_one_eight),
                context.getString(R.string.option_two_eight),
                context.getString(R.string.option_three_eight),
                context.getString(R.string.option_four_eight),
                2,
                VALUE_CATEGORY_JAVA))
        questionData.add(QuestionData(
                9,
                context.getString(R.string.question_nine),
                context.getString(R.string.option_one_nine),
                context.getString(R.string.option_two_nine),
                context.getString(R.string.option_three_nine),
                "",
                2,
                VALUE_CATEGORY_JAVA))
        questionData.add(QuestionData(
                10,
                context.getString(R.string.question_ten),
                context.getString(R.string.option_one_ten),
                context.getString(R.string.option_two_ten),
                context.getString(R.string.option_three_ten),
                context.getString(R.string.option_four_ten),
                1,
                VALUE_CATEGORY_JAVA))

        questionData.add(QuestionData(
                11,
                context.getString(R.string.question_eleven),
                context.getString(R.string.option_one_eleven),
                context.getString(R.string.option_two_eleven),
                context.getString(R.string.option_three_eleven),
                context.getString(R.string.option_four_eleven),
                4,
                VALUE_CATEGORY_ANDROID))

        db.getDbAccess().addAllQuestion(questionData)
    }
}
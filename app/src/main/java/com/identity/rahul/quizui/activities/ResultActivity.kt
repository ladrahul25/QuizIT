package com.identity.rahul.quizui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.identity.rahul.quizui.R
import com.identity.rahul.quizui.utils.Constants.Companion.CORRECT_ANSWERS
import com.identity.rahul.quizui.utils.Constants.Companion.TOTAL_QUESTIONS
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {

    var totalQuestions: Int = 0
    var correctQuestions: Int = 0
    lateinit var result : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        if (null != intent.extras){
            totalQuestions = intent!!.getIntExtra(TOTAL_QUESTIONS, 0)
            correctQuestions = intent!!.getIntExtra(CORRECT_ANSWERS, 0)
        }
        showResult()
    }

    private fun showResult() {
        result = correctQuestions.toString() + "/" + totalQuestions.toString()
        textResult.setText(result)
    }
}

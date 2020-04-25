package com.identity.rahul.quizui.activities

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.identity.rahul.quizui.R
import com.identity.rahul.quizui.database.DatabaseCreatorUtil
import com.identity.rahul.quizui.models.QuestionData
import com.identity.rahul.quizui.utils.Constants.Companion.CORRECT_ANSWERS
import com.identity.rahul.quizui.utils.Constants.Companion.QUESTION_CATEGORY
import com.identity.rahul.quizui.utils.Constants.Companion.TOTAL_QUESTIONS
import com.identity.rahul.quizui.utils.Constants.Companion.VALUE_CATEGORY_MIX
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity(), View.OnClickListener {

    var questionDataList: List<QuestionData>? = null
    lateinit var questionData: QuestionData
    var questionID: Int = 0
    var correctAnswers: Int = 0
    var totalQuestions: Int = 0
    var category: String = ""
    var answerSubmitted = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (null != intent.extras){
            category = intent!!.getStringExtra(QUESTION_CATEGORY)
        }

        optionOne.setOnClickListener(this)
        optionTwo.setOnClickListener(this)
        optionThree.setOnClickListener(this)
        optionFour.setOnClickListener(this)
        lifecycleScope.launch(Dispatchers.Default) {
            if (category.equals(VALUE_CATEGORY_MIX)){
                questionDataList = DatabaseCreatorUtil(applicationContext).getAllQuestion()
            } else {
                questionDataList = DatabaseCreatorUtil(applicationContext).getQuestionFromCategory(category)
            }
            totalQuestions = questionDataList?.size!!
            loadQuestion()
        }
    }

    private fun loadQuestion() {
        answerSubmitted = false
        lifecycleScope.launch(Dispatchers.Main) {
            quesionIndex.setText(getString(R.string.question_index, (questionID+1), totalQuestions))
            if (null != questionDataList && questionID < questionDataList!!.size && null != questionDataList?.get(questionID)) {
                questionData = questionDataList!!.get(questionID)
                cardView.setText(questionData.question)

                if(!TextUtils.isEmpty(questionData.optionOne)){
                    optionOne.setText(questionData.optionOne)
                    optionOne.visibility = VISIBLE
                } else {
                    optionOne.visibility = GONE
                }
                if(!TextUtils.isEmpty(questionData.optionTwo)){
                    optionTwo.setText(questionData.optionTwo)
                    optionTwo.visibility = VISIBLE
                } else {
                    optionTwo.visibility = GONE
                }
                if(!TextUtils.isEmpty(questionData.optionThree)){
                    optionThree.setText(questionData.optionThree)
                    optionThree.visibility = VISIBLE
                } else {
                    optionThree.visibility = GONE
                }
                if(!TextUtils.isEmpty(questionData.optionFour)){
                    optionFour.setText(questionData.optionFour)
                    optionFour.visibility = VISIBLE
                } else {
                    optionFour.visibility = GONE
                }
            } else {
                setOptionsEnable(false)
                val intent = Intent(baseContext, ResultActivity::class.java)
                intent.putExtra(TOTAL_QUESTIONS, totalQuestions)
                intent.putExtra(CORRECT_ANSWERS, correctAnswers)
                startActivity(intent)
                finish()
            }
        }

    }

    private fun clearAllQuestionData() {
        lifecycleScope.launch(Dispatchers.Main) {
            optionOne.setBackgroundResource(R.drawable.ripple_effect_button)
            optionTwo.setBackgroundResource(R.drawable.ripple_effect_button)
            optionThree.setBackgroundResource(R.drawable.ripple_effect_button)
            optionFour.setBackgroundResource(R.drawable.ripple_effect_button)
            optionOne.setTextColor(ContextCompat.getColor(applicationContext, R.color.colorPrimary))
            optionTwo.setTextColor(ContextCompat.getColor(applicationContext, R.color.colorPrimary))
            optionThree.setTextColor(ContextCompat.getColor(applicationContext, R.color.colorPrimary))
            optionFour.setTextColor(ContextCompat.getColor(applicationContext, R.color.colorPrimary))
            answer.visibility = GONE
            setOptionsEnable(true)
        }
    }

    fun goNext(view: View) {
        if (optionOne.isSelected || optionTwo.isSelected ||optionThree.isSelected || optionFour.isSelected || answerSubmitted){
            lifecycleScope.launch(Dispatchers.Default) {
                clearAllQuestionData()
                questionID++
                loadQuestion()
            }
        } else {
            answer.visibility = VISIBLE
            answer.setText(getString(R.string.select_any_option))
        }
    }

    fun goPrev(view: View) {
        Toast.makeText(applicationContext, "Prev Clicked!", Toast.LENGTH_SHORT).show()
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.optionOne -> {
                verifyAnswer(1, view)
            }
            R.id.optionTwo -> {
                verifyAnswer(2, view)
            }
            R.id.optionThree -> {
                verifyAnswer(3, view)
            }
            R.id.optionFour -> {
                verifyAnswer(4, view)
            }
        }
    }

    private fun verifyAnswer(answerOption: Int, view: View) {
        answerSubmitted = true
        if (questionData.answer == answerOption) {
            (view as Button).setBackgroundResource(R.drawable.ripple_effect_button_right_answer)
            view.setTextColor(ContextCompat.getColor(this, R.color.colorWhite))
            answer.visibility = VISIBLE
            answer.setText(getString(R.string.select_correct_answer))
            correctAnswers++
        } else {
            (view as Button).setBackgroundResource(R.drawable.ripple_effect_button_wrong_answer)
            answer.visibility = VISIBLE
            view.setTextColor(ContextCompat.getColor(this, R.color.colorWhite))
            answer.setText(getString(R.string.select_wrong_answer))
        }
        setOptionsEnable(false)
    }

    private fun setOptionsEnable(b: Boolean) {
        optionOne.isEnabled = b
        optionTwo.isEnabled = b
        optionThree.isEnabled = b
        optionFour.isEnabled = b
    }
}

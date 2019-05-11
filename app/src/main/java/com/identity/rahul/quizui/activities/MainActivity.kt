package com.identity.rahul.quizui.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.VISIBLE
import android.widget.Button
import android.widget.Toast
import com.identity.rahul.quizui.R
import com.identity.rahul.quizui.database.DatabaseCreatorUtil
import com.identity.rahul.quizui.models.AndroidQuestionData
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var questionData: AndroidQuestionData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        optionOne.setOnClickListener(this)
        optionTwo.setOnClickListener(this)
        optionThree.setOnClickListener(this)
        optionFour.setOnClickListener(this)
        DatabaseCreatorUtil(this).createAndroidDatabase()
    }

    fun goNext(view: View){
        Toast.makeText(applicationContext,"Next Clicked!",Toast.LENGTH_SHORT).show()
    }

    fun goPrev(view: View){
        Toast.makeText(applicationContext,"Prev Clicked!",Toast.LENGTH_SHORT).show()
    }

    override fun onClick(view: View?) {
        when(view?.id){
            R.id.optionOne ->{verifyAnswer(1,view)}
            R.id.optionTwo ->{verifyAnswer(2,view)}
            R.id.optionThree ->{verifyAnswer(3,view)}
            R.id.optionFour ->{verifyAnswer(4,view)}
        }
    }

    private fun verifyAnswer(answerOption: Int, view : View) {
        if (questionData.answer == answerOption){
            (view as Button).setBackgroundColor(resources.getColor(R.color.greenAnswer))
            answer.visibility = VISIBLE
            answer.setText(getString(R.string.select_correct_answer))
        } else {
            (view as Button).setBackgroundColor(resources.getColor(R.color.redAnswer))
            answer.visibility = VISIBLE
            answer.setText(getString(R.string.select_wrong_answer))
        }
        setOptionsEnable(false);
    }

    private fun setOptionsEnable(b: Boolean) {
        optionOne.isEnabled = b
        optionTwo.isEnabled = b
        optionThree.isEnabled = b
        optionFour.isEnabled = b
    }
}

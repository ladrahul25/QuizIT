package com.identity.rahul.quizui.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.identity.rahul.quizui.R
import com.identity.rahul.quizui.database.DatabaseCreatorUtil
import com.identity.rahul.quizui.utils.Constants
import com.identity.rahul.quizui.utils.Constants.Companion.VALUE_CATEGORY_ANDROID
import com.identity.rahul.quizui.utils.Constants.Companion.VALUE_CATEGORY_JAVA
import com.identity.rahul.quizui.utils.Constants.Companion.VALUE_CATEGORY_MIX
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        javaQuestions.setOnClickListener(this)
        androidQuestions.setOnClickListener(this)
        androidJavaQuestions.setOnClickListener(this)

        lifecycleScope.launch(Dispatchers.Default) {
            if (DatabaseCreatorUtil(applicationContext).getQuestionCount() <= 0) {
                DatabaseCreatorUtil(applicationContext).createQuestionDatabase()
            }
        }
    }


    override fun onClick(view: View?) {
        val intent = Intent(this, MainActivity::class.java)

        when(view?.id){
            R.id.javaQuestions ->{
                intent.putExtra(Constants.QUESTION_CATEGORY, VALUE_CATEGORY_JAVA)
            }
            R.id.androidQuestions ->{
                intent.putExtra(Constants.QUESTION_CATEGORY, VALUE_CATEGORY_ANDROID)
            }
            R.id.androidJavaQuestions ->{
                intent.putExtra(Constants.QUESTION_CATEGORY, VALUE_CATEGORY_MIX)
            }
        }
        startActivity(intent)
    }
}

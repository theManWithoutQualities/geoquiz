package com.example.geoquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.geoquiz.domain.Question
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var mYesButton: Button
    lateinit var mNoButton: Button
    lateinit var mPreviousButton: Button
    lateinit var mNextButton: Button
    lateinit var mQuestionText: TextView
    lateinit var questions: List<Question>
    var questionPosition: Int = 0
    var currentAnswer: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)
        questions = Arrays.asList(
            Question(R.string.question_china, true),
            Question(R.string.question_belarus, false),
            Question(R.string.question_russia, true),
            Question(R.string.question_italy, false)
        )
        mYesButton = findViewById(R.id.yes_button)
        mNoButton = findViewById(R.id.no_button)
        mPreviousButton = findViewById(R.id.button_previous)
        mNextButton = findViewById(R.id.button_next)
        mQuestionText = findViewById(R.id.text_question)
        mQuestionText.setText(questions[questionPosition].textResource)
        currentAnswer = questions[questionPosition].answer
        mQuestionText.setOnClickListener {
            Log.d("Geo", "click text")
            questionPosition = (questionPosition + 1) % questions.size
            showQuestion()
        }
        mNextButton.setOnClickListener {
            Log.d("Geo", "click next")
            questionPosition = (questionPosition + 1) % questions.size
            showQuestion()
        }
        mPreviousButton.setOnClickListener {
            Log.d("Geo", "click previous")
            questionPosition--
            if (questionPosition < 0) {
                questionPosition = questions.size - 1
            }
            showQuestion()
        }
        mYesButton.setOnClickListener{
            Log.d("Geo", "click yes")
            showToast(true, currentAnswer)
        }
        mNoButton.setOnClickListener{
            Log.d("Geo", "click no")
            showToast(false, currentAnswer)
        }
    }
    private fun showToast(answer: Boolean, rightAnswer: Boolean) {
        if (answer == rightAnswer) {
            val toastCorrect = Toast.makeText(this, "Corrrect!", Toast.LENGTH_LONG)
            toastCorrect.setGravity(Gravity.LEFT, 0, 0)
            toastCorrect.show()
        } else {
            val toastCorrect = Toast.makeText(this, "Incorrrect!", Toast.LENGTH_LONG)
            toastCorrect.setGravity(Gravity.LEFT, 0, 0)
            toastCorrect.show()
        }
    }
    private fun showQuestion() {
        mQuestionText.setText(questions[questionPosition].textResource)
        currentAnswer = questions[questionPosition].answer
    }
}

package pl.edu.agh.toik.quizapp

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_quiz.*
import pl.edu.agh.toik.quizapp.api.QuizApi
import pl.edu.agh.toik.quizapp.model.Difficulty
import pl.edu.agh.toik.quizapp.model.Question

const val EXTRA_STATISTICS = "pl.edu.agh.toik.quizapp.STATISTICS"

class QuizActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)
        val quantity = intent.getIntExtra(EXTRA_QUANTITY, 5)
        val difficulty = Difficulty.valueOf(intent.getStringExtra(EXTRA_DIFFICULTY)!!)
        val category = intent.getStringExtra(EXTRA_CATEGORY)!!
        val questions = QuizApi().getQuizQuestions(quantity, difficulty, category)
        start(questions)
    }

    private fun start(questions: Array<Question>) {
        val statistics = Statistics()
        val iterator = questions.iterator()
        if (iterator.hasNext()) {
            ask(iterator.next(), statistics)
        }
        buttonNext.setOnClickListener {
            if (iterator.hasNext()) {
                ask(iterator.next(), statistics)
            } else {
                startStatisticsActivity(statistics);
            }
        }
    }

    private fun ask(question: Question, statistics: Statistics) {
        textViewQuestion.text = question.body
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, question.answers!!)
        listViewQuestions.adapter = adapter
        listViewQuestions.onItemClickListener = OnItemClickListener { adapterView, view, i, l ->
            val color = if (i == question.correctAnswer) {
                statistics.updatePositiveAnswers()
                "#00FF00"
            } else {
                statistics.updateNegativeAnswers()
                "#FF0000"
            }
            view.setBackgroundColor(Color.parseColor(color))
            listViewQuestions.onItemClickListener = null
        }
    }

    private fun startStatisticsActivity(statistics: Statistics) {
        val intent = Intent(this, StatisticsActivity::class.java).apply {
            putExtra(EXTRA_STATISTICS, statistics)
        }
        startActivity(intent)
    }
}

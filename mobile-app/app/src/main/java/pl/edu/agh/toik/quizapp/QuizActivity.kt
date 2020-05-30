package pl.edu.agh.toik.quizapp

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import kotlinx.android.synthetic.main.activity_quiz.*
import pl.edu.agh.toik.quizapp.model.Question

const val EXTRA_STATISTICS = "pl.edu.agh.toik.quizapp.STATISTICS"

class QuizActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)
        val questionsString = intent.getStringExtra(EXTRA_QUESTIONS)!!
        val mapper = jacksonObjectMapper()
        val questions: Array<Question> = mapper.readValue(questionsString)
        start(questions)
    }

    private fun start(questions: Array<Question>) {
        val statistics = Statistics()
        val iterator = questions.iterator()
        if (iterator.hasNext()) {
            ask(iterator.next(), statistics)
        }
        buttonNext.setOnClickListener {
            if (!iterator.hasNext()) {
                startStatisticsActivity(statistics);
                return@setOnClickListener
            }
            ask(iterator.next(), statistics)
        }
    }

    private fun ask(question: Question, statistics: Statistics) {
        textViewQuestion.text = question.body
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, question.answers!!)
        listViewQuestions.adapter = adapter
        listViewQuestions.onItemClickListener = OnItemClickListener { adapterView, view, i, l ->
            val color = if (i == question.correctAnswer) {
                statistics.updatePositiveAnswers()
                "#d1f7ff"
            } else {
                statistics.updateNegativeAnswers()
                "#ff2a6d"
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

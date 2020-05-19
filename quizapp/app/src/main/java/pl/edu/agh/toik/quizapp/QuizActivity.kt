package pl.edu.agh.toik.quizapp

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_quiz.*
import pl.edu.agh.toik.quizapp.model.Difficulty
import pl.edu.agh.toik.quizapp.model.Question

const val EXTRA_STATISTICS = "pl.edu.agh.toik.quizapp.STATISTICS"

class QuizActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)
        val request = intent.getStringExtra(EXTRA_REQUEST)
        val questions = requestQuestions(request!!)
        start(questions)
    }

    private fun requestQuestions(request: String): List<Question> {
        val question1 = Question(123,
            "Sport",
            Difficulty.easy.value,
            "raz dwa trzy",
            0,
            arrayOf("raz", "dwa", "trzy", "cztery")
        )
        val question2 = Question(123,
            "Sport",
            Difficulty.easy.value,
            "cztery pięć sześć",
            1,
            arrayOf("cztery", "pięć", "szcześć")
        )
        return arrayListOf(question1, question2)
    }

    private fun start(questions: List<Question>) {
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

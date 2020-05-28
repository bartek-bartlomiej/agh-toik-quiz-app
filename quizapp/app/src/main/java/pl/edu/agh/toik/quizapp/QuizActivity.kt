package pl.edu.agh.toik.quizapp

import android.app.AlertDialog
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
        val category = intent.getLongExtra(EXTRA_CATEGORY, 0)
        val questions = QuizApi().getQuizQuestions(quantity, difficulty, category)
        start(questions)
    }

    private fun start(questions: Array<Question>) {
        if (questions.isEmpty()) {
            AlertDialog.Builder(this)
                .setTitle("No such questions")
                .setMessage("We could not find questions for specified category and difficulty")
                .setPositiveButton(android.R.string.yes) {
                        _, _ -> navigateUpTo(Intent(baseContext, MainActivity::class.java))
                }
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show()
        }
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

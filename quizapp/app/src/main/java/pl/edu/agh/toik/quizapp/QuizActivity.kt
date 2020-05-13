package pl.edu.agh.toik.quizapp

import android.graphics.Color
import android.os.Bundle
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_quiz.*
import pl.edu.agh.toik.quizapp.model.Difficulty
import pl.edu.agh.toik.quizapp.model.Question


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
        val iterator = questions.iterator()
        ask(iterator.next())
        buttonNext.setOnClickListener {
            ask(iterator.next())
        }
    }

    private fun ask(question: Question) {
        textViewQuestion.text = question.body
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, question.answers!!)
        listViewQuestions.adapter = adapter
        listViewQuestions.onItemClickListener = OnItemClickListener { adapterView, view, i, l ->
            val color = if (i == question.correctAnswer) {
                "#00FF00"
            } else {
                "#FF0000"
            }
            view.setBackgroundColor(Color.parseColor(color))
            listViewQuestions.onItemClickListener = null;
        }
    }
}

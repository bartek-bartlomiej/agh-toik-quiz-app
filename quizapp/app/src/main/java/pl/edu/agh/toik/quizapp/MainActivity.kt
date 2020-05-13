package pl.edu.agh.toik.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import kotlinx.android.synthetic.main.activity_main.*
import pl.edu.agh.toik.quizapp.model.Difficulty

const val EXTRA_REQUEST = "pl.edu.agh.toik.quizapp.REQUEST"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            Difficulty.values()
        )
        spinnerDifficulty.adapter = adapter;
    }

    fun startQuiz(view: View) {
        val editTextCategory = findViewById<EditText>(R.id.editTextCategory)
        val editTextQuantity = findViewById<EditText>(R.id.editTextQuantity)
        val spinnerDifficulty = findViewById<Spinner>(R.id.spinnerDifficulty)

        val request = createRequest(editTextCategory.text.toString(),
            Integer.parseInt(editTextQuantity.text.toString()),
            spinnerDifficulty.selectedItem.toString())
        val intent = Intent(this, QuizActivity::class.java).apply {
            putExtra(EXTRA_REQUEST, request)
        }
        startActivity(intent)
    }

    private fun createRequest(category: String, quantity: Int, difficulty: String): String {
        return ""
    }
}

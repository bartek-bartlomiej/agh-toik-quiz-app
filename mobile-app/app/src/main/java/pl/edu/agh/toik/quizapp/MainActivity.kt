package pl.edu.agh.toik.quizapp

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.os.StrictMode
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import kotlinx.android.synthetic.main.activity_main.*
import org.angmarch.views.NiceSpinner
import pl.edu.agh.toik.quizapp.api.CategoriesApi
import pl.edu.agh.toik.quizapp.api.QuizApi
import pl.edu.agh.toik.quizapp.model.Category
import pl.edu.agh.toik.quizapp.model.Difficulty
import pl.edu.agh.toik.quizapp.model.Question

const val EXTRA_QUESTIONS = "pl.edu.agh.toik.quizapp.QUESTIONS"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        spinnerDifficulty.attachDataSource(Difficulty.values().asList())
        val categories = CategoriesApi().getCategories().toList()
        val categoriesNames = categories.map{it.name!!}
        spinnerCategory.attachDataSource(categoriesNames)
        buttonStart.setOnClickListener { startQuiz(it, categories) }
    }

    private fun startQuiz(view: View, categories: List<Category>) {
        val spinnerCategory = findViewById<NiceSpinner>(R.id.spinnerCategory)
        val editTextQuantity = findViewById<EditText>(R.id.editTextQuantity)
        val spinnerDifficulty = findViewById<NiceSpinner>(R.id.spinnerDifficulty)
        if (editTextQuantity.text.isEmpty()) {
            editTextQuantity.error = "Quantity is required"
            return
        }
        val quantity = Integer.parseInt(editTextQuantity.text.toString())
        val difficulty = Difficulty.valueOf(spinnerDifficulty.selectedItem.toString())
        val categoryName = spinnerCategory.selectedItem.toString()
        val categoryId = findCategoryId(categoryName, categories)
        val questions = getQuestions(quantity, difficulty, categoryId)
        if (questions.isEmpty()) {
            showNoQuestionsDialog()
            return
        }
        val mapper = jacksonObjectMapper()
        val questionsString = mapper.writeValueAsString(questions)
        val intent = Intent(this, QuizActivity::class.java).apply {
            putExtra(EXTRA_QUESTIONS, questionsString)
        }
        startActivity(intent)
    }

    private fun getQuestions(quantity: Int, difficulty: Difficulty, categoryId: Long): Array<Question> {
        return QuizApi().getQuizQuestions(quantity, difficulty, categoryId)
    }

    private fun showNoQuestionsDialog() {
        AlertDialog.Builder(this)
            .setTitle("No such questions")
            .setMessage("We could not find questions for specified category and difficulty")
            .setPositiveButton(android.R.string.yes) { _, _ -> }
            .setIcon(android.R.drawable.ic_dialog_alert)
            .show()
    }

    private fun findCategoryId(categoryName: String, categories: List<Category>): Long {
        for (category in categories) {
            if (category.name.equals(categoryName)) {
                return category.id!!
            }
        }
        return 0
    }
}

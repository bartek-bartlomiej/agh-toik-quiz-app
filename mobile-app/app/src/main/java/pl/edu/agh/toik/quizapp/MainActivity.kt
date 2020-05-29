package pl.edu.agh.toik.quizapp

import android.content.Intent
import android.os.Bundle
import android.os.StrictMode
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.angmarch.views.NiceSpinner
import pl.edu.agh.toik.quizapp.api.CategoriesApi
import pl.edu.agh.toik.quizapp.model.Category
import pl.edu.agh.toik.quizapp.model.Difficulty

const val EXTRA_CATEGORY = "pl.edu.agh.toik.quizapp.CATEGORY"
const val EXTRA_QUANTITY = "pl.edu.agh.toik.quizapp.QUANTITY"
const val EXTRA_DIFFICULTY = "pl.edu.agh.toik.quizapp.DIFFICULTY"

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
        val difficulty = spinnerDifficulty.selectedItem.toString()
        val categoryName = spinnerCategory.selectedItem.toString()
        val categoryId = findCategoryId(categoryName, categories)
        val intent = Intent(this, QuizActivity::class.java).apply {
            putExtra(EXTRA_QUANTITY, quantity)
            putExtra(EXTRA_DIFFICULTY, difficulty)
            putExtra(EXTRA_CATEGORY, categoryId)
        }
        startActivity(intent)
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

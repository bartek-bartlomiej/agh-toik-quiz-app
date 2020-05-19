package pl.edu.agh.toik.quizapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_statistics.*


class StatisticsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_statistics)
        val statistics = intent.getSerializableExtra(EXTRA_STATISTICS) as Statistics
        textViewPositiveAnswersNum.text = statistics.positiveAnswers.toString()
        textViewNegativeAnswersNum.text = statistics.negativeAnswers.toString()
    }

    fun returnToMainActivity(view: View) {
        navigateUpTo(Intent(baseContext, MainActivity::class.java))
    }
}

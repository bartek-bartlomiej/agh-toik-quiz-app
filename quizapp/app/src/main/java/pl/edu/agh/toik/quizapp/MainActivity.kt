package pl.edu.agh.toik.quizapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import pl.edu.agh.toik.quizapp.model.Question

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Question()
    }
}

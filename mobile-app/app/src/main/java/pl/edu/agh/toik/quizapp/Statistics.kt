package pl.edu.agh.toik.quizapp

import java.io.Serializable

class Statistics : Serializable {
    var positiveAnswers = 0;
    var negativeAnswers = 0;

    fun updatePositiveAnswers() {
        positiveAnswers++
    }

    fun updateNegativeAnswers() {
        negativeAnswers++
    }
}

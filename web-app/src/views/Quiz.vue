<template>
  <div class="columns is-centered">
    <div class="column is-two-thirds-tablet is-half-desktop">
      <template v-if="isInProgress">
        <question
          v-bind="{
            ordinal: currentQuestionOrdinal,
            ...currentQuestionData,
            questionsQuantity
          }"
          @progress-requested="progress"
          @answer-given="saveAnswer" />
      </template>
      <template v-else>
        <quiz-summary
          :correct-answers-quantity="correctAnswersQuantity"
          :questions-quantity="questionsQuantity" />
      </template>
    </div>
  </div>
</template>

<script>
import Question from '@/components/quiz/QuizQuestion'
import QuizSummary from '@/components/quiz/QuizSummary'
import client from '@/api'
import { QuizQueryDTO } from '@/api/model'

export default {
  name: 'Quiz',
  components: { QuizSummary, Question },
  data: function () {
    return {
      questions: [],
      answers: [],
      currentQuestionIndex: 0
    }
  },
  computed: {
    currentQuestionData () {
      const currentQuestion = this.questions[this.currentQuestionIndex]
      return {
        body: currentQuestion.body,
        correctAnswer: currentQuestion.correctAnswer,
        answers: currentQuestion.answers
      }
    },
    currentQuestionOrdinal () {
      return this.currentQuestionIndex + 1
    },
    correctAnswersQuantity () {
      return this.answers.reduce((quantity, answerIndex, index) => {
        return answerIndex === this.questions[index].correctAnswer ? quantity + 1 : quantity
      }, 0)
    },
    questionsQuantity () {
      return this.questions.length
    },
    isInProgress () {
      return this.currentQuestionIndex < this.questionsQuantity
    }
  },
  methods: {
    startQuiz (questions) {
      this.questions = questions
      this.currentQuestionIndex = 0
    },
    saveAnswer (givenAnswer) {
      this.answers.push(givenAnswer)
    },
    progress () {
      this.currentQuestionIndex += 1
    }
  },
  beforeRouteEnter (to, from, next) {
    const query = QuizQueryDTO.parseParameters(to.params)
    client.getQuizQuestions(query)
      .then(response => {
        if (response.data.length === 0) {
          next(Error('No question found for this category'))
          return
        }
        next(vm => vm.startQuiz(response.data))
      })
      .catch(error => {
        console.error('Could not get questions: ' + error.toString())
        next(Error('Could not get questions from server'))
      })
  }
}
</script>

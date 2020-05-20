<template>
  <div class="section">
    <div class="container is-widescreen">
      <div class="columns is-centered">
        <div class="column is-two-thirds-tablet is-half-desktop">
          <question
            v-if="isInProgress"
            :question-data="currentQuestionData"
            :questions-quantity="questionsQuantity"
            @progress-requested="progress"
            @answer-given="saveAnswer"/>
          <quiz-summary
            :correct-answers-quantity="correctAnswersQuantity"
            :questions-quantity="questionsQuantity"
            v-else />
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import Question from '../components/quiz/Question'
import QuizSummary from '../components/quiz/QuizSummary'

export default {
  name: 'Quiz',
  components: { QuizSummary, Question },
  data: function () {
    return {
      questions: [
        {
          answers: ['Thanos', 'Shaggy', 'Superman', 'Saitama'],
          body: 'Who is the most powerful hero in the world?',
          category: 'Memes',
          correctAnswer: 1,
          difficulty: 'hard',
          id: 0
        },
        {
          answers: ['X', '22', '?', '4'],
          body: '2+2=?',
          category: 'Arithmetic',
          correctAnswer: 3,
          difficulty: 'easy',
          id: 1
        },
        {
          answers: ['Gray wolf', 'Fox', 'Bear', 'Owl'],
          body: 'What animal was the Sheriff of Nottingham in Disney’s Robin Hood?',
          category: 'Movies',
          correctAnswer: 0,
          difficulty: 'hard',
          id: 2
        }
      ],
      answers: [],
      currentQuestionIndex: 0
    }
  },
  computed: {
    currentQuestionData () {
      const currentQuestion = this.questions[this.currentQuestionIndex]
      return {
        ordinal: this.currentQuestionOrdinal,
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
        return quantity + (answerIndex === this.questions[index].correctAnswer ? 1 : 0)
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
    saveAnswer (givenAnswer) {
      this.answers.push(givenAnswer)
    },
    progress () {
      this.currentQuestionIndex += 1
    }
  }
}
</script>

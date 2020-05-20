<template>
  <div class="section">
    <div class="container is-widescreen">
      <div class="columns is-centered">
        <div class="column is-two-thirds-tablet is-half-desktop">
          <nav class="level">
            <div class="level-item">
              <h1 class="title is-4 is-spaced">Question {{ currentQuestionIndex + 1 }} ouf of {{ questions.length }}</h1>
            </div>
          </nav>
          <section class="section">
            <h2 class="subtitle has-text-centered">{{ currentQuestion.body }}</h2>
            <div class="buttons">
              <template v-for="(answer, index) in currentQuestion.answers">
                <answer-button
                  :index="index"
                  :is-correct-answer="index === currentQuestion.correctAnswer"
                  :is-question-answered="isCurrentQuestionAnswered"
                  :key="index"
                  :text="answer"
                  @answer-selected="saveAnswer"/>
              </template>
            </div>
            <nav class="level">
              <div class="level-left"/>
              <div class="level-right">
                <div class="level-item">
                  <b-button
                    :disabled="!isCurrentQuestionAnswered"
                    type="is-primary">
                    Next
                  </b-button>
                </div>
              </div>
            </nav>
          </section>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import AnswerButton from '../components/quiz/AnswerButton'

export default {
  name: 'Quiz',
  components: { AnswerButton },
  data: function () {
    return {
      questions: [
        {
          answers: ['Thanos', 'Shaggy', 'Superman', 'Saitama'],
          body: 'Who is the most powerful hero in the world?',
          category: 'movies',
          correctAnswer: 1,
          difficulty: 'hard',
          id: 0
        }
      ],
      answers: [],
      currentQuestionIndex: 0
    }
  },
  computed: {
    currentQuestion () {
      return this.questions[this.currentQuestionIndex]
    },
    isCurrentQuestionAnswered () {
      return this.answers.length === this.currentQuestionIndex + 1
    }
  },
  methods: {
    saveAnswer (answerIndex) {
      this.answers.push(answerIndex)
    }
  }
}
</script>

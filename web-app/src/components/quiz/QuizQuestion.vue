<template>
  <div>
    <nav class="level">
      <div class="level-item">
        <h1 class="title is-4">Question {{ ordinal }} out of {{ questionsQuantity }}</h1>
      </div>
    </nav>
    <section class="section">
      <h2 class="subtitle has-text-centered">{{ body }}</h2>
      <div class="buttons">
        <template v-for="(answer, index) in answers">
          <answer-button
            :index="index"
            :text="answer"
            :question-ordinal="ordinal"
            :is-correct-answer="index === correctAnswer"
            :is-question-answered="answered"
            :key="index"
            @answer-selected="updateAnswer"/>
        </template>
      </div>
      <nav class="level">
        <div class="level-left"/>
        <div class="level-right">
          <div class="level-item">
            <b-button
              :disabled="!answered"
              @click="$emit('progress-requested')"
              type="is-primary">
              {{ ordinal === questionsQuantity ? 'Finish' : 'Next' }}
            </b-button>
          </div>
        </div>
      </nav>
    </section>
  </div>
</template>

<script>
import AnswerButton from '@/components/quiz/QuizQuestionAnswerButton'

export default {
  name: 'Question',
  components: { AnswerButton },
  props: {
    ordinal: Number,
    body: String,
    correctAnswer: Number,
    answers: Array,
    questionsQuantity: Number
  },
  data: function () {
    return {
      chosenAnswer: undefined,
      answered: false
    }
  },
  watch: {
    ordinal () {
      this.answered = false
      this.chosenAnswer = undefined
    }
  },
  methods: {
    updateAnswer (index) {
      this.chosenAnswer = index
      this.giveAnswer()
    },
    giveAnswer () {
      this.answered = true
      this.$emit('answer-given', this.chosenAnswer)
    }
  }
}
</script>

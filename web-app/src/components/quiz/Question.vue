<template>
  <div>
    <nav class="level">
      <div class="level-item">
        <h1 class="title is-4">Question {{ questionData.ordinal }} ouf of {{ questionsQuantity }}</h1>
      </div>
    </nav>
    <section class="section">
      <h2 class="subtitle has-text-centered">{{ questionData.body }}</h2>
      <div class="buttons">
        <template v-for="(answer, index) in questionData.answers">
          <answer-button
            :index="index"
            :text="answer"
            :question-ordinal="questionData.ordinal"
            :is-correct-answer="index === questionData.correctAnswer"
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
              {{ questionData.ordinal === questionsQuantity ? 'Finish' : 'Next' }}
            </b-button>
          </div>
        </div>
      </nav>
    </section>
  </div>
</template>

<script>
import AnswerButton from './AnswerButton'

export default {
  name: 'Question',
  components: { AnswerButton },
  props: {
    questionData: {
      ordinal: Number,
      body: String,
      correctAnswer: Number,
      answers: Array
    },
    questionsQuantity: Number
  },
  data: function () {
    return {
      chosenAnswer: undefined,
      answered: false
    }
  },
  watch: {
    'questionData.ordinal' () {
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

<template>
  <form @submit.prevent="handleSubmit">
    <section class="section box">
      <b-field horizontal label="Category">
        <quiz-category-input v-model="quizData.category"/>
      </b-field>

      <b-field horizontal label="Difficulty">
        <quiz-difficulty-input v-model="quizData.difficulty"/>
      </b-field>

      <b-field horizontal label="Quantity">
        <quiz-quantity-input v-model="quizData.quantity"/>
      </b-field>
    </section>
    <div class="buttons is-centered">
      <b-button type="is-primary" size="is-large" native-type="submit" :disabled="!dataValid" :loading="loading">Start Quiz!</b-button>
      <b-button type="is-white" tag="router-link" :to="{ name: 'Categories' }">Manage questions</b-button>
    </div>
  </form>
</template>

<script>
import QuizCategoryInput from './HomeNewQuizCategoryInput'
import QuizDifficultyInput from './HomeNewQuizDifficultyInput'
import QuizQuantityInput from './HomeNewQuizQuantityInput'
import { Difficulty } from '@/api/model'

export default {
  name: 'HomeQuizParametersForm',
  components: { QuizQuantityInput, QuizCategoryInput, QuizDifficultyInput },
  data: function () {
    return {
      loading: false,
      quizData: {
        category: undefined,
        difficulty: Difficulty.get('medium'),
        quantity: 5
      }
    }
  },
  computed: {
    dataValid () {
      return typeof this.quizData.category === 'string' &&
        Difficulty.has(this.quizData.difficulty) &&
        (this.quizData.quantity > 0 && this.quizData.quantity <= 100)
    }
  },
  methods: {
    handleSubmit () {
    }
  }
}
</script>

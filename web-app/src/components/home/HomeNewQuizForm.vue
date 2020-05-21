<template>
  <form>
    <section class="section box">
      <b-field horizontal label="Category">
        <quiz-category-input v-model="category"/>
      </b-field>

      <b-field horizontal label="Difficulty">
        <quiz-difficulty-input v-model="difficulty"/>
      </b-field>

      <b-field horizontal label="Quantity">
        <quiz-quantity-input v-model="quantity"/>
      </b-field>
    </section>
    <div class="buttons is-centered">
      <b-button type="is-primary" size="is-large">Start Quiz!</b-button>
      <b-button type="is-white" tag="router-link" :to="{ name: 'Categories' }">Manage questions</b-button>
    </div>
  </form>
</template>

<script>
import { Difficulty } from '@/api/model'

import QuizCategoryInput from './HomeNewQuizCategoryInput'
import QuizDifficultyInput from './HomeNewQuizDifficultyInput'
import QuizQuantityInput from './HomeNewQuizQuantityInput'

export default {
  name: 'HomeQuizParametersForm',
  components: { QuizQuantityInput, QuizCategoryInput, QuizDifficultyInput },
  data: function () {
    return {
      category: undefined,
      difficulty: Difficulty.get('medium'),
      quantity: 5
    }
  }
}
</script>

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
      <b-button type="is-primary" size="is-large" native-type="submit" :disabled="!quizData.isValid()" :loading="loading">Start Quiz!</b-button>
      <b-button type="is-white" tag="router-link" :to="{ name: 'Categories' }">Manage questions</b-button>
    </div>
  </form>
</template>

<script>
import QuizCategoryInput from '@/components/home/NewQuizCategoryInput'
import QuizDifficultyInput from '@/components/home/NewQuizDifficultyInput'
import QuizQuantityInput from '@/components/home/NewQuizQuantityInput'
import { Difficulty, QuizQueryDTO } from '@/api/model'

export default {
  name: 'QuizParametersForm',
  components: { QuizQuantityInput, QuizCategoryInput, QuizDifficultyInput },
  data: function () {
    return {
      loading: false,
      quizData: new QuizQueryDTO(undefined, Difficulty.get('medium'), 5)
    }
  },
  methods: {
    handleSubmit () {
      this.$router.push({ name: 'Quiz', params: this.quizData.toParameters() },
        () => {},
        error => {
          this.$buefy.toast.open({
            duration: 3000,
            message: `${error.message}…`,
            position: 'is-bottom',
            type: 'is-warning'
          })
        })
    }
  }
}
</script>

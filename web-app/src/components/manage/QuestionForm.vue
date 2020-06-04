<template>
  <form @submit.prevent="handleSubmit">
    <div class="modal-card">
      <header class="modal-card-head">
        <p class="modal-card-title">{{mode === 'add' ? 'New' : 'Edit'}} question</p>
      </header>
      <section class="modal-card-body">
        <b-field label="Category">
          <category-input v-model="question.category.id" />
        </b-field>
        <b-field label="Difficulty">
          <difficulty-input v-model="question.difficulty" />
        </b-field>
        <b-field label="Content">
          <b-input
            placeholder="Question content"
            required
            v-model="question.body"
            type="text">
          </b-input>
        </b-field>
        <b-field label="Answers">
          <div class="control">
            <b-field
              v-for="(_, index) in question.answers"
              :key="index"
            >
              <b-input
                placeholder="Question content"
                required
                :icon-pack="index === question.correctAnswer ? 'fas' : 'far'"
                :icon-right="index === question.correctAnswer ? 'check-circle' : 'circle'"
                @icon-right-click="question.correctAnswer = index"
                icon-right-clickable
                v-model="question.answers[index]"
                type="text">
              </b-input>
            </b-field>
          </div>
        </b-field>
      </section>
      <footer class="modal-card-foot">
        <b-button
          native-type="submit"
          type="is-primary"
          :disabled="!isValid || pending"
          :loading="pending"
        >
          {{mode === 'add' ? 'Add' : 'Update'}} question
        </b-button>
        <b-button
          @click="$parent.close()"
        >
          Cancel
        </b-button>
      </footer>
    </div>
  </form>
</template>

<script>
import apiOperationMixin from '../../mixin/apiOperationMixin'
import DifficultyInput from '../DifficultyInput'
import CategoryInput from '../CategoryInput'

const mixinData = {
  add: () => ({
    operationName: 'addQuestion',
    shouldShowSuccessToast: true,
    shouldRetry: false,
    consoleErrorMessage: 'Add question failed',
    toastSuccessMessage: 'Question added successfully',
    toastErrorMessage: 'Could not add question. Please, try again.'
  }),
  edit: () => ({
    operationName: 'updateQuestion',
    shouldShowSuccessToast: true,
    shouldRetry: false,
    consoleErrorMessage: 'Update category failed',
    toastSuccessMessage: 'Question updated successfully',
    toastErrorMessage: 'Could not update question. Please, try again.'
  })
}

export default {
  name: 'QuestionForm',
  components: { CategoryInput, DifficultyInput },
  mixins: [apiOperationMixin],
  props: {
    mode: {
      validator: function (value) {
        return ['add', 'edit'].indexOf(value) !== -1
      },
      required: true
    }
  },
  data: function () {
    return {
      question: {
        answers: [
          'Ala',
          'Ola',
          'Ela',
          'Marta'
        ],
        body: '?',
        category: {
          id: 11,
          name: ''
        },
        correctAnswer: 0,
        difficulty: 'medium',
        id: 0
      },
      ...mixinData[this.mode]()
    }
  },
  computed: {
    isValid () {
      return false
    }
  },
  methods: {
    handleSubmit () {
      console.log('todo')
    }
  }
}
</script>

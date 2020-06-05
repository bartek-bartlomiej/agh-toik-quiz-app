<template>
  <form @submit.prevent="handleSubmit">
    <div class="modal-card">
      <header class="modal-card-head">
        <p class="modal-card-title">{{mode === 'add' ? 'New' : 'Edit'}} question</p>
      </header>
      <section class="modal-card-body">
        <b-field
          label="Category"
          :type="{
            'is-danger': typeof validationErrors.categoryId !== 'undefined'
          }"
          :message="validationErrors.categoryId"
        >
          <category-input v-model="question.category.id" />
        </b-field>
        <b-field
          label="Difficulty"
          :type="{
            'is-danger': typeof validationErrors.difficulty !== 'undefined'
          }"
          :message="validationErrors.difficulty"
        >
          <difficulty-input v-model="question.difficulty" />
        </b-field>
        <b-field
          label="Content"
          :type="{
            'is-danger': typeof validationErrors.body !== 'undefined'
          }"
          :message="validationErrors.body"
        >
          <b-input
            placeholder="Question content"
            v-model="question.body"
            type="text">
          </b-input>
        </b-field>
        <b-field
          label="Answers"
          :type="{
            'is-danger': typeof validationErrors.answers !== 'undefined'
          }"
          :message="validationErrors.answers"
        >
          <div class="control">
            <b-field
              grouped
              v-for="index of question.answers.keys()"
              :key="index"
            >
              <div class="control">
                <b-button
                  :type="{'is-primary' : index === question.correctAnswer}"
                  :icon-pack="index === question.correctAnswer ? 'fas' : 'far'"
                  :icon-right="index === question.correctAnswer ? 'check-circle' : 'circle'"
                  @click="question.correctAnswer = index"/>
              </div>
              <b-field
                expanded
                :type="{
                  'is-danger': typeof validationErrors[`answer-${index}`] !== 'undefined'
                }"
                :message="validationErrors[`answer-${index}`]"
              >
                <b-input
                  placeholder="Question answer"
                  v-model="question.answers[index]"
                  type="text" />
              </b-field>
              <div class="control">
                <b-button
                  icon-pack="fa"
                  icon-right="trash-alt"
                  @click="deleteAnswer(index)" />
              </div>
            </b-field>
            <b-button
              icon-pack="fa"
              icon-right="plus"
              @click="question.answers.push('')"
            />
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
        <delete-question-button
          v-if="mode === 'edit'"
          :question-id="question.id"
          @question-removed="handleQuestionRemoved"
        />
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
import { Question, Difficulty } from '../../api/model'
import DeleteQuestionButton from './category/DeleteQuestionButton'

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
  components: { DeleteQuestionButton, CategoryInput, DifficultyInput },
  mixins: [apiOperationMixin],
  props: {
    mode: {
      validator: function (value) {
        return ['add', 'edit'].indexOf(value) !== -1
      },
      required: true
    },
    categoryId: {
      type: Number,
      required: true
    },
    categories: {
      type: Array,
      required: true
    },
    questionValues: {
      type: Question,
      default () {
        return new Question(
          0,
          '',
          [],
          undefined,
          0,
          Difficulty.get('medium')
        )
      }
    }
  },
  data: function () {
    const question = this.questionValues
    question.category.id = this.categoryId
    return {
      question: question,
      ...mixinData[this.mode]()
    }
  },
  computed: {
    validationErrors () {
      return this.question.validate(this.categories)
    },
    isValid () {
      return Object.keys(this.validationErrors).length === 0
    },
    operationData () {
      return this.question.toData()
    }
  },
  methods: {
    deleteAnswer (answerIndex) {
      if (this.question.correctAnswer === answerIndex) {
        this.question.correctAnswer = undefined
      }
      this.question.answers = this.question.answers.filter((_, index) => index !== answerIndex)
    },
    handleSubmit () {
      this.performOperation()
    },
    handleOperationSucceeded (question) {
      this.$emit(this.mode === 'add' ? 'question-added' : 'question-changed', question)
      this.$parent.close()
    },
    handleQuestionRemoved () {
      this.$emit('question-removed', this.question)
      this.$parent.close()
    }
  }
}
</script>

<template>
  <b-button
    type="is-danger"
    outlined
    @click="confirmDelete"
  >
    Delete
  </b-button>
</template>

<script>
import apiOperationMixin from '@/mixin/apiOperationMixin'

const mixinData = {
  operationName: 'deleteQuestion',
  shouldRetry: false,
  shouldShowSuccessToast: true,
  toastSuccessMessage: 'Question deleted',
  consoleErrorMessage: 'Could not delete question',
  toastErrorMessage: 'Could not delete question. Please, try again.'
}

export default {
  name: 'DeleteQuestionButton',
  mixins: [apiOperationMixin],
  props: {
    questionId: {
      type: Number,
      required: true
    }
  },
  data: function () {
    return {
      ...mixinData
    }
  },
  computed: {
    operationParams () {
      return {
        questionId: this.questionId
      }
    }
  },
  methods: {
    confirmDelete () {
      this.$buefy.dialog.confirm({
        title: 'Deleting question',
        message: 'Are you sure you want to <strong>delete</strong> this question?',
        confirmText: 'Delete question',
        type: 'is-danger',
        hasIcon: true,
        iconPack: 'fa',
        onConfirm: () => this.performOperation()
      })
    },
    handleOperationSucceeded () {
      this.$emit('question-removed')
    }
  }
}
</script>

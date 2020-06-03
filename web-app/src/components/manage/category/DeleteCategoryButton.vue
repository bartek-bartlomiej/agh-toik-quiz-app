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
  operationName: 'deleteCategory',
  shouldRetry: false,
  shouldShowSuccessToast: true,
  toastSuccessMessage: 'Category deleted',
  consoleErrorMessage: 'Could not delete category',
  toastErrorMessage: 'Could not delete category. Please, try again.'
}

export default {
  name: 'DeleteCategoryButton',
  mixins: [apiOperationMixin],
  props: {
    categoryId: {
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
        categoryId: this.categoryId
      }
    }
  },
  methods: {
    confirmDelete () {
      this.$buefy.dialog.confirm({
        title: 'Deleting category',
        message: 'Are you sure you want to <strong>delete</strong> this category?',
        confirmText: 'Delete category',
        type: 'is-danger',
        hasIcon: true,
        iconPack: 'fa',
        onConfirm: () => this.performOperation()
      })
    },
    handleOperationSucceeded () {
      this.$emit('category-deleted')
    }
  }
}
</script>

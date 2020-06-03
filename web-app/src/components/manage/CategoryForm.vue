<template>
  <form @submit.prevent="handleSubmit">
    <div class="modal-card">
      <header class="modal-card-head">
        <p class="modal-card-title">New category</p>
      </header>
      <section class="modal-card-body">
        <b-field label="Name">
          <b-input
            placeholder="Unique name category"
            required
            type="text"
            v-model="name"
            validation-message="Name cannot be empty">
          </b-input>
        </b-field>
      </section>
      <footer class="modal-card-foot">
        <b-button native-type="submit" type="is-primary" :disabled="!nameChanged">{{mode === 'add' ? 'Add' : 'Update'}} category</b-button>
        <b-button @click="$parent.close()">Cancel</b-button>
      </footer>
    </div>
  </form>
</template>

<script>
import apiOperationMixin from '@/mixin/apiOperationMixin'

const mixinData = {
  add: () => ({
    operationName: 'addCategory',
    shouldShowSuccessToast: true,
    shouldRetry: false,
    consoleErrorMessage: 'Add category failed',
    toastSuccessMessage: 'Category added successfully',
    toastErrorMessage: 'Could not add category. Please, try again.'
  }),
  edit: () => ({
    operationName: 'updateCategory',
    shouldShowSuccessToast: true,
    shouldRetry: false,
    consoleErrorMessage: 'Update category failed',
    toastSuccessMessage: 'Category updated successfully',
    toastErrorMessage: 'Could not update category. Please, try again.'
  })
}

export default {
  name: 'CategoryForm',
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
      default: 0
    },
    categoryOriginalName: {
      type: String
    }
  },
  data: function () {
    return {
      name: this.categoryOriginalName,
      ...mixinData[this.mode]()
    }
  },
  computed: {
    nameChanged () {
      return this.name !== this.categoryOriginalName
    },
    operationData () {
      return {
        id: this.categoryId,
        name: this.name
      }
    }
  },
  methods: {
    handleSubmit () {
      this.performOperation()
    },
    handleOperationSucceeded (category) {
      this.$emit('data-changed', category)
      this.$parent.close()
    }
  }
}
</script>

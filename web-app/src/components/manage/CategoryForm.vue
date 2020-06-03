<template>
  <form @submit.prevent="handleSubmit">
    <div class="modal-card">
      <header class="modal-card-head">
        <p class="modal-card-title">{{mode === 'add' ? 'New' : 'Edit'}} category</p>
      </header>
      <section class="modal-card-body">
        <b-field
          label="Name"
          :type="{
            'is-success': dirty && isValid,
            'is-danger': dirty && !isValid
          }"
          :message="dirty ? validationErrors : []">
          <b-input
            placeholder="Unique name category"
            required
            type="text"
            v-model="name"
            @input="dirty = true">
          </b-input>
        </b-field>
      </section>
      <footer class="modal-card-foot">
        <b-button
          native-type="submit"
          type="is-primary"
          :disabled="!isValid || pending"
          :loading="pending"
        >
          {{mode === 'add' ? 'Add' : 'Update'}} category
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
import { Category } from '@/api/model'
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
    categories: {
      type: Array,
      required: true
    }
  },
  data: function () {
    return {
      dirty: false,
      name: this.initialName,
      ...mixinData[this.mode]()
    }
  },
  computed: {
    initialName () {
      const category = this.categories.find(category => category.categoryId === this.categoryId)
      return category !== undefined
        ? category.name
        : undefined
    },
    category () {
      return new Category(this.categoryId, this.name)
    },
    validationErrors () {
      return this.category.validate(this.categories)
    },
    isValid () {
      return this.validationErrors.length === 0
    },
    operationData () {
      return this.category.toData()
    }
  },
  methods: {
    handleSubmit () {
      this.performOperation()
    },
    handleOperationSucceeded (category) {
      this.$emit('category-changed', category)
      this.$parent.close()
    }
  }
}
</script>

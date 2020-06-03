<template>
  <div>
    <b-button
      type="is-primary"
      :outlined="outlined"
      @click="isCategoryModalVisible = true"
    >
      {{mode === 'add' ? 'New category' : 'Edit'}}
    </b-button>
    <b-modal
      :active.sync="isCategoryModalVisible"
      has-modal-card
      trap-focus
      destroy-on-hide
    >
      <category-form
        :mode="mode"
        :category-id="categoryId"
        :categories="categories"
        @category-changed="handleCategoryChanged"/>
    </b-modal>
  </div>
</template>

<script>
import CategoryForm from '@/components/manage/CategoryForm'

export default {
  name: 'ChangeCategoryButton',
  components: { CategoryForm },
  props: {
    outlined: {
      type: Boolean,
      default: false
    },
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
    },
    handleCategoryChanged: {
      type: Function,
      required: true
    }
  },
  data: function () {
    return {
      isCategoryModalVisible: false
    }
  }
}
</script>

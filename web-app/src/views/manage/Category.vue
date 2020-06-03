<template>
  <div class="section">
    <div class="container is-widescreen">
      <nav class="level">
        <div class="level-left">
          <div class="level-item">
            <h1 class="title">Category {{ name }}</h1>
          </div>
        </div>
        <div class="level-right">
          <div class="level-item">
            <b-button type="is-danger" outlined @click="confirmDelete">Delete</b-button>
          </div>
          <div class="level-item">
            <b-button type="is-primary" outlined @click="isCategoryModalVisible = true">Edit</b-button>
            <b-modal
              :active.sync="isCategoryModalVisible"
              has-modal-card
              trap-focus
              destroy-on-hide
            >
              <category-form
                :category-id="id"
                :category-original-name="name"
                mode="edit"
                @data-changed="handleCategoryUpdated"/>
            </b-modal>
          </div>
          <div class="level-item">
            <b-button type="is-primary">Add question</b-button>
          </div>
        </div>
      </nav>
      <questions-table
        :category-id="id" />
    </div>
  </div>
</template>

<script>
import CategoryForm from '@/components/manage/CategoryForm'
import QuestionsTable from '@/components/manage/category/CategoryQuestionsTable'
import client from '@/api'
import { CategoryDTO } from '@/api/model'

export default {
  name: 'Category',
  components: { CategoryForm, QuestionsTable },
  props: {
    id: {
      type: Number,
      required: true
    }
  },
  data () {
    return {
      isCategoryModalVisible: false,
      categories: []
    }
  },
  computed: {
    name () {
      const category = this.categories.find(category => category.id === this.id)
      return category !== undefined ? category.name : undefined
    }
  },
  methods: {
    saveCategories (categories) {
      this.categories = categories
    },
    confirmDelete () {
      this.$buefy.dialog.confirm({
        title: 'Deleting category',
        message: 'Are you sure you want to <strong>delete</strong> this category?',
        confirmText: 'Delete category',
        type: 'is-danger',
        hasIcon: true,
        iconPack: 'fa',
        onConfirm: () => console.log('TODO delete :o)')
      })
    },
    handleCategoryUpdated (updatedCategory) {
      this.categories = this.categories.map(category =>
        category.id === this.id
          ? updatedCategory
          : category
      )
    }
  },
  beforeRouteEnter (to, from, next) {
    const categoryDTO = CategoryDTO.parseParameters(to.params)
    client.getCategories()
      .then(response => {
        const categories = response.data
        if (!categoryDTO.isValid(categories)) {
          if (from.name === null) {
            next({ name: 'Home', replace: true })
            return
          }
          next(Error('Category not found'))
          return
        }
        next(vm => vm.saveCategories(categories))
      })
      .catch(error => {
        console.error('Could not get categories: ' + error.toString())
        if (from.name === null) {
          next({ name: 'Home', replace: true })
          return
        }
        next(Error('Could not get categories from server'))
      })
  }
}
</script>

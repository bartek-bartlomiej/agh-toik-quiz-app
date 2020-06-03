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
            <delete-category-button
              :category-id="id"
              @category-deleted="handleCategoryDeleted" />
          </div>
          <div class="level-item">
            <change-category-button
              mode="edit"
              outlined
              :category-id="id"
              :categories="categories"
              :handle-category-changed="handleCategoryUpdated" />
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
import DeleteCategoryButton from '@/components/manage/category/DeleteCategoryButton'
import ChangeCategoryButton from '@/components/manage/ChangeCategoryButton'
import QuestionsTable from '@/components/manage/category/CategoryQuestionsTable'
import client from '@/api'
import { CategoryIdDTO } from '@/api/model'

export default {
  name: 'Category',
  components: { ChangeCategoryButton, DeleteCategoryButton, QuestionsTable },
  props: {
    id: {
      type: Number,
      required: true
    }
  },
  data () {
    return {
      categories: []
    }
  },
  computed: {
    name () {
      const category = this.categories.find(category => category.id === this.id)
      return category !== undefined
        ? category.name
        : undefined
    }
  },
  methods: {
    saveCategories (categories) {
      this.categories = categories
    },
    handleCategoryUpdated (updatedCategory) {
      this.categories = this.categories.map(category =>
        category.id === this.id
          ? updatedCategory
          : category
      )
    },
    handleCategoryDeleted () {
      this.$router.replace({ name: 'Categories' })
    }
  },
  beforeRouteEnter (to, from, next) {
    const categoryDTO = CategoryIdDTO.parseParameters(to.params)
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

<template>
  <div class="section">
    <div class="container is-widescreen">
      <nav class="level">
        <div class="level-left">
          <div class="level-item">
            <h1 class="title">Category "{{ name }}"</h1>
          </div>
        </div>
        <div class="level-right">
          <div class="level-item">
            <delete-category-button
              :category-id="id"
              @category-deleted="handleCategoryDeleted" />
          </div>
          <div class="level-item">
            <b-button
              type="is-primary"
              outlined
              @click="showCategoryModal"
            >
              Edit
            </b-button>
          </div>
          <div class="level-item">
            <b-button
              type="is-primary"
              @click="showQuestionModal"
            >
              Add question
            </b-button>
          </div>
        </div>
      </nav>
      <questions-table
        :category-id="id"
        @show-modal-requested="showQuestionModal"
      />
      <b-modal
        :active.sync="categoryModalVisible"
        has-modal-card
        trap-focus
        destroy-on-hide
      >
        <category-modal
          mode="edit"
          :category-id="id"
          :categories="categories"
          @category-changed="handleCategoryUpdated"
        />
      </b-modal>
      <b-modal
        :active.sync="questionModalVisible"
        has-modal-card
        trap-focus
        destroy-on-hide
      >
        <question-form
          mode="add"
          :category-id="id"
          :categories="categories"
          @question-changed="handleQuestionAdded"
        />
      </b-modal>
    </div>
  </div>
</template>

<script>
import DeleteCategoryButton from '@/components/manage/category/DeleteCategoryButton'
import QuestionsTable from '@/components/manage/category/CategoryQuestionsTable'
import QuestionForm from '../../components/manage/QuestionForm'
import CategoryModal from '../../components/manage/CategoryForm'
import client from '@/api'
import { CategoryIdDTO } from '@/api/model'

export default {
  name: 'Category',
  components: { CategoryModal, QuestionForm, DeleteCategoryButton, QuestionsTable },
  props: {
    id: {
      type: Number,
      required: true
    }
  },
  data () {
    return {
      categories: [],
      categoryModalVisible: false,
      questionModalVisible: false
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
    },
    handleQuestionAdded (question) {
      // todo pass to question table
    },
    showCategoryModal () {
      this.categoryModalVisible = true
    },
    showQuestionModal () {
      this.questionModalVisible = true
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

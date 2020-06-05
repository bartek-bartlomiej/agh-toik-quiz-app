<template>
  <div class="section">
    <div class="container is-widescreen">
      <nav class="level">
        <div class="level-left">
          <div class="level-item">
            <h1 class="title">Categories</h1>
          </div>
        </div>
        <div class="level-right">
          <div class="level-item">
            <b-button
              type="is-primary"
              @click="showModal"
            >
              Add category
            </b-button>
          </div>
        </div>
      </nav>
      <template v-if="pending">
        <div class="columns is-multiline">
          <category-summary-skeleton
            v-for="index in 4"
            :key="index" />
        </div>
      </template>
      <template v-else>
        <template v-if="categorySummaries.length > 0">
          <div class="columns is-multiline">
          <category-summary
            v-for="(summary, index) in sortedSummaries"
            v-bind="summary"
            :key="index" />
          </div>
        </template>
        <template v-else>
          <div class="content">
            No categories defined. <a @click="showModal">Add a new one</a>.
          </div>
        </template>
      </template>
      <b-modal
        :active.sync="categoryModalVisible"
        has-modal-card
        trap-focus
        destroy-on-hide
      >
        <category-form
          mode="add"
          :categories="categories"
          @category-changed="handleCategoryAdded" />
      </b-modal>
    </div>
  </div>
</template>

<script>
import CategorySummary from '@/components/manage/categories/CategorySummary'
import CategorySummarySkeleton from '@/components/manage/categories/CategorySummarySkeleton'
import CategoryForm from '@/components/manage/CategoryForm'
import apiOperationMixin from '@/mixin/apiOperationMixin'

const mixinData = {
  operationName: 'getCategories',
  shouldRetry: true,
  consoleErrorMessage: 'Could not get categories',
  toastErrorMessage: 'Could not display categories…'
}

export default {
  name: 'Categories',
  components: { CategorySummarySkeleton, CategorySummary, CategoryForm },
  mixins: [apiOperationMixin],
  data: function () {
    return {
      categories: [],
      categoryModalVisible: false,
      ...mixinData
    }
  },
  computed: {
    categorySummaries () {
      return this.categories.map(category => ({
        ...category,
        quantity: NaN
      }))
    },
    sortedSummaries () {
      return [...this.categorySummaries]
        .sort((one, other) => one.name.localeCompare(other.name))
    }
  },
  methods: {
    showModal () {
      this.categoryModalVisible = true
    },
    handleOperationSucceeded (categories) {
      this.categories = categories
    },
    handleCategoryAdded (category) {
      this.categories.push(category)
    }
  },
  mounted () {
    this.performOperation()
  }
}
</script>

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
            <change-category-button
              mode="add"
              :categories="categories"
              :handle-category-changed="handleCategoryAdded"
            />
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
              No categories defined. <a @click="isNewCategoryModalVisible = true">Add a new one</a>.
            </div>
          </template>
        </template>
    </div>
  </div>
</template>

<script>
import ChangeCategoryButton from '../../components/manage/ChangeCategoryButton'
import CategorySummary from '@/components/manage/categories/CategorySummary'
import CategorySummarySkeleton from '@/components/manage/categories/CategorySummarySkeleton'
import apiOperationMixin from '@/mixin/apiOperationMixin'

const mixinData = {
  operationName: 'getCategories',
  shouldRetry: true,
  consoleErrorMessage: 'Could not get categories',
  toastErrorMessage: 'Could not display categories…'
}

export default {
  name: 'Categories',
  components: { ChangeCategoryButton, CategorySummarySkeleton, CategorySummary },
  mixins: [apiOperationMixin],
  data: function () {
    return {
      categories: [],
      isNewCategoryModalVisible: false,
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

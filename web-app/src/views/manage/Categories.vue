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
            <b-button type="is-primary" @click="isNewCategoryModalVisible = true">New category</b-button>
            <b-modal
              :active.sync="isNewCategoryModalVisible"
              has-modal-card
              trap-focus
              destroy-on-hide
            >
              <new-category-form @category-added="handleCategoryAdded"/>
            </b-modal>
          </div>
        </div>
      </nav>
        <template v-if="loading">
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
import NewCategoryForm from '@/components/manage/categories/NewCategoryForm'
import CategorySummary from '@/components/manage/categories/CategorySummary'
import CategorySummarySkeleton from '@/components/manage/categories/CategorySummarySkeleton'
import { loadingMixin } from '@/mixin/loadingMixin'

const loadingCategoryMixin = loadingMixin({
  operationName: 'getCategories',
  consoleError: 'Could not get categories',
  toastMessage: 'Could not display categories',
  shouldRetry: true
})

export default {
  name: 'Categories',
  components: { CategorySummarySkeleton, NewCategoryForm, CategorySummary },
  mixins: [loadingCategoryMixin],
  data: function () {
    return {
      isNewCategoryModalVisible: false
    }
  },
  computed: {
    categorySummaries () {
      if (this.externalData === undefined) {
        return []
      }
      return [...this.externalData]
        .map(category => ({
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
    handleCategoryAdded (category) {
      this.categorySummaries.push({
        ...category,
        quantity: 0
      })
    }
  },
  mounted () {
    this.loadData()
  }
}
</script>

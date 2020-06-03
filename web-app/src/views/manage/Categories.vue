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
              <category-form
                mode="add"
                @data-changed="handleCategoryAdded"/>
            </b-modal>
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
import CategoryForm from '@/components/manage/CategoryForm'
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
  components: { CategorySummarySkeleton, CategoryForm, CategorySummary },
  mixins: [apiOperationMixin],
  data: function () {
    return {
      categorySummaries: [],
      isNewCategoryModalVisible: false,
      ...mixinData
    }
  },
  computed: {
    sortedSummaries () {
      return [...this.categorySummaries]
        .sort((one, other) => one.name.localeCompare(other.name))
    }
  },
  methods: {
    handleOperationSucceeded (categories) {
      this.categorySummaries = categories.map(category => ({
        ...category,
        quantity: NaN
      }))
    },
    handleCategoryAdded (category) {
      this.categorySummaries.push({
        ...category,
        quantity: 0
      })
    }
  },
  mounted () {
    this.performOperation()
  }
}
</script>

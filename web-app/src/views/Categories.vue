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
import client from '../api'
import NewCategoryForm from '../components/categories/NewCategoryForm'
import CategorySummary from '../components/categories/CategorySummary'
import CategorySummarySkeleton from '../components/categories/CategorySummarySkeleton'

export default {
  name: 'Categories',
  components: { CategorySummarySkeleton, NewCategoryForm, CategorySummary },
  data: function () {
    return {
      isNewCategoryModalVisible: false,
      loading: true,
      categorySummaries: []
    }
  },
  computed: {
    sortedSummaries () {
      return [...this.categorySummaries]
        .sort((one, other) => one.name.localeCompare(other.name))
    }
  },
  methods: {
    loadCategories () {
      this.loading = true
      client.getCategories()
        .then(response => {
          this.categorySummaries = response.data
            .map(category => ({
              ...category,
              quantity: NaN
            }))
          this.loading = false
        })
        .catch(error => {
          console.error('Could not get categories: ' + error.toString())
          this.$buefy.toast.open({
            duration: 3000,
            message: 'Could not display categories. Will try again in 5 seconds.',
            position: 'is-bottom',
            type: 'is-warning'
          })
          setTimeout(() => this.loadCategories(), 5000)
        })
    },
    handleCategoryAdded (category) {
      this.categorySummaries.push({
        ...category,
        quantity: 0
      })
    }
  },
  mounted () {
    this.loadCategories()
  }
}
</script>

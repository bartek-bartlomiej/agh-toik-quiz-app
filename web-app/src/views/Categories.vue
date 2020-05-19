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
      <div class="columns is-multiline">
        <template v-if="loading">
          <category-summary-skeleton
            v-for="index in 4"
            :key="index" />
        </template>
        <template v-else>
          <category-summary
            v-for="(summary, index) in sortedSummaries"
            v-bind="summary"
            :key="index" />
        </template>
      </div>
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
            .map(name => ({
              name,
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
          // DEBUG
          // setTimeout(() => this.loadCategories(), 5000)
        })
        // DEBUG
        .finally(() => {
          this.categorySummaries = ['r', 'c', 'w', 'e', 'y']
            .map(name => ({
              name,
              quantity: NaN
            }))
          this.loading = false
        })
    },
    handleCategoryAdded (name) {
      this.categorySummaries.push({
        name,
        quantity: 0
      })
    }
  },
  mounted () {
    this.loadCategories()
  }
}
</script>

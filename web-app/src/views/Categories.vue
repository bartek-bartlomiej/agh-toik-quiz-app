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
              <new-category-form/>
            </b-modal>
          </div>
        </div>
      </nav>
      <div class="columns is-multiline">
        <category-summary v-for="i in 3" v-bind:key="i"/>
      </div>
    </div>
  </div>
</template>

<script>
import CategorySummary from '../components/categories/CategorySummary'
import NewCategoryForm from '../components/categories/NewCategoryForm'

export default {
  name: 'Categories',
  components: { NewCategoryForm, CategorySummary },
  data: function () {
    return {
      isNewCategoryModalVisible: false
    }
  }
}
</script>

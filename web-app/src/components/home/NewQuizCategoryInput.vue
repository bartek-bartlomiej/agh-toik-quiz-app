<template>
  <b-field grouped position="is-centered">
    <b-select
      placeholder="Select a category"
      :loading="loading"
      :disabled="categories.length === 0"
      v-model="currentValue"
      @input="$emit('input', $event)"
    >
      <option
        v-for="(option, index) in categories"
        :value="option.id"
        :key="index">
        {{ option.name }}
      </option>
    </b-select>
  </b-field>
</template>

<script>
import client from '@/api'

export default {
  name: 'HomeNewQuizCategoryInput',
  props: {
    value: Number
  },
  data: function () {
    return {
      currentValue: this.value,
      loading: true,
      categories: []
    }
  },
  methods: {
    loadCategories () {
      this.loading = true
      client.getCategories()
        .then(response => {
          this.categories = response.data
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
    }
  },
  mounted () {
    this.loadCategories()
  }
}
</script>

<template>
  <b-field grouped position="is-centered">
    <b-select
      :placeholder="loading ? 'Loading categories…' : 'Select a category'"
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
import { loadingMixin } from '@/mixin/loadingMixin'

const loadingCategoryMixin = loadingMixin({
  operation: {
    name: 'getCategories'
  },
  consoleError: 'Could not get categories',
  toastMessage: 'Could not display categories',
  shouldRetry: true
})

export default {
  name: 'HomeNewQuizCategoryInput',
  mixins: [loadingCategoryMixin],
  props: {
    value: Number
  },
  data: function () {
    return {
      currentValue: this.value
    }
  },
  computed: {
    categories () {
      if (this.externalData === undefined) {
        return []
      }
      return [...this.externalData]
        .sort((one, other) => one.name.localeCompare(other.name))
    }
  },
  mounted () {
    this.loadData()
  }
}
</script>

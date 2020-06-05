<template>
  <b-field grouped position="is-centered">
    <b-select
      :placeholder="pending ? 'Loading categories…' : 'Select a category'"
      :loading="pending"
      :disabled="categories.length === 0"
      v-model="currentValue"
      @input="$emit('input', $event)"
    >
      <option
        v-for="(option, index) in sortedCategories"
        :value="option.id"
        :key="index">
        {{ option.name }}
      </option>
    </b-select>
  </b-field>
</template>

<script>
import apiOperationMixin from '@/mixin/apiOperationMixin'

const mixinData = {
  operationName: 'getCategories',
  shouldRetry: true,
  consoleErrorMessage: 'Could not get categories',
  toastErrorMessage: 'Could not display categories…'
}

export default {
  name: 'CategoryInput',
  mixins: [apiOperationMixin],
  props: {
    value: Number
  },
  data: function () {
    return {
      categories: [],
      currentValue: this.value,
      ...mixinData
    }
  },
  computed: {
    sortedCategories () {
      return [...this.categories]
        .sort((one, other) => one.name.localeCompare(other.name))
    }
  },
  methods: {
    handleOperationSucceeded (categories) {
      this.categories = categories
    }
  },
  mounted () {
    this.performOperation()
  }
}
</script>

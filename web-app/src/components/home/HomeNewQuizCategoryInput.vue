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
        :value="option"
        :key="index">
        {{ option }}
      </option>
    </b-select>
  </b-field>
</template>

<script>
export default {
  name: 'HomeNewQuizCategoryInput',
  props: {
    value: String
  },
  data: function () {
    return {
      currentValue: this.value,
      loading: true,
      categories: []
    }
  }
}
</script>

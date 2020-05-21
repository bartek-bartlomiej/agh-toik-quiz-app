<template>
  <b-field addons position="is-centered">
    <b-radio-button
      v-for="(difficulty, index) in difficulties"
      :key="index"
      :native-value="difficulty"
      type="is-primary"
      v-model="currentValue"
      @input="$emit('input', $event)"
    >
      <span class="is-capitalized">{{ difficulty }}</span>
    </b-radio-button>
  </b-field>
</template>

<script>
import { Difficulty } from '@/api/model'

export default {
  name: 'HomeNewQuizDifficultyInput',
  props: {
    value: {
      type: String
    }
  },
  data: function () {
    return {
      currentValue: this.value
    }
  },
  computed: {
    difficulties () {
      return [...Difficulty.values()]
    }
  }
}
</script>

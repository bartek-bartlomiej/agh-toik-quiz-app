<template>
  <b-button
    :class="{ 'is-inactive': isQuestionAnswered }"
    :outlined="!selected"
    :type="!isQuestionAnswered ? 'is-primary' : (isCorrectAnswer ? 'is-success' : (selected ? 'is-danger' : 'is-light'))"
    @click="select"
    expanded
    size="is-large">
    {{ text }}
  </b-button>
</template>

<script>
export default {
  name: 'answer-button',
  props: {
    text: String,
    index: Number,
    questionOrdinal: Number,
    isQuestionAnswered: Boolean,
    isCorrectAnswer: Boolean
  },
  data: function () {
    return {
      selected: false
    }
  },
  watch: {
    questionOrdinal () {
      this.selected = false
    }
  },
  methods: {
    select () {
      this.$el.blur()
      this.selected = true
      this.$emit('answer-selected', this.index)
    }
  }
}
</script>

<style scoped>
  .is-inactive {
    pointer-events: none;
  }
</style>

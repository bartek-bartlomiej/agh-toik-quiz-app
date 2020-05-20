<template>
  <b-button
    :class="{ 'is-inactive': isQuestionAnswered }"
    :outlined="!isQuestionAnswered ? true : !selected"
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
  data: function () {
    return {
      selected: false
    }
  },
  props: {
    text: String,
    index: Number,
    questionId: Number,
    isQuestionAnswered: Boolean,
    isCorrectAnswer: Boolean
  },
  watch: {
    questionId () {
      this.selected = false
    }
  },
  methods: {
    select () {
      this.$el.blur()
      this.$emit('answer-selected', this.index)
      this.selected = true
    }
  }
}
</script>

<style>
  .is-inactive {
    pointer-events: none;
  }
</style>

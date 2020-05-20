<template>
  <div class="has-text-centered">
    <div class="section">
      <b-icon
        id="emoji"
        pack="fas"
        :icon="icon"
        type="is-primary"
      />
    </div>
    <h1 class="title">Quiz is finished!</h1>
    <section class="section">
      <p class="heading">Your result is</p>
      <h1 class="title is-1">{{ correctAnswersQuantity }} / {{ questionsQuantity }}</h1>
      <p>{{ message }}</p>
    </section>
    <nav class="level">
        <div class="level-item">
          <b-button type="is-primary">
            Start next Quiz!
          </b-button>
        </div>
      </nav>
  </div>
</template>

<script>
const iconNames = [
  'tired',
  'frown-open',
  'meh',
  'grin',
  'grin-bean',
  'grin-stars'
]

const messages = [
  'Next time will be better!',
  'You can better!',
  'Not bad!',
  'Good!',
  'Very good!',
  'Wow!'
]

export default {
  name: 'quiz-summary',
  props: {
    correctAnswersQuantity: Number,
    questionsQuantity: Number
  },
  computed: {
    percentValue () {
      return this.correctAnswersQuantity / this.questionsQuantity
    },
    icon () {
      return iconNames[Math.floor(this.percentValue * (iconNames.length - 1))]
    },
    message () {
      return messages[Math.floor(this.percentValue * (messages.length - 1))]
    }
  }
}
</script>

<style scoped>
  #emoji {
    width: 10rem;
    height: 10rem;
    font-size: 8em;
  }
</style>

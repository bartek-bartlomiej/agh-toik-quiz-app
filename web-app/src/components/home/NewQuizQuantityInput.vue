<template>
  <b-field grouped position="is-centered">
    <b-numberinput
      min="1"
      max="100"
      icon-pack="fa"
      controls-position="compact"
      v-model="currentValue"
      @input="$emit('input', $event)"
    />
  </b-field>
</template>

<script>
export default {
  name: 'HomeNewQuizQuantityInput',
  props: {
    value: Number
  },
  data: function () {
    return {
      currentValue: this.value
    }
  }
}
</script>

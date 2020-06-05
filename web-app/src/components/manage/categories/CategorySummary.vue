<template>
  <div class="column is-half-tablet is-one-third-desktop">
    <div
      class="notification"
      :class="{ 'is-primary': hover, clickable: hover }"
      @mouseover="hover = true"
      @mouseleave="hover = false"
      @click="goToCategoryView"
    >
      <div class="media center">
        <div class="media-content">
          <div class="content">
            <p>
              <span class="title is-5">{{ name }}</span>
              <br>
              <span class="subtitle is-7">{{ questionsQuantity }}</span>
            </p>
          </div>
        </div>
        <div class="media-right">
          <figure class="image is-32x32">
            <b-icon
              pack="fa"
              icon="icons"
              size="is-medium"/>
          </figure>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { CategoryIdDTO } from '../../../api/model'

export default {
  name: 'CategorySummary',
  props: {
    id: Number,
    name: String,
    quantity: Number
  },
  data: function () {
    return {
      hover: false
    }
  },
  computed: {
    questionsQuantity () {
      return `${this.quantity} question${this.quantity !== 1 ? 's' : ''}`
    }
  },
  methods: {
    goToCategoryView () {
      const categoryIdDTO = new CategoryIdDTO(this.id)
      this.$router.push({ name: 'Category', params: categoryIdDTO.toParameters() },
        () => {},
        error => {
          this.$buefy.toast.open({
            duration: 3000,
            message: error.message,
            position: 'is-bottom',
            type: 'is-warning'
          })
        })
    }
  }
}
</script>

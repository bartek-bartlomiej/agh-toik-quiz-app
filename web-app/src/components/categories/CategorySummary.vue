<template>
  <div class="column is-half-tablet is-one-third-desktop">
    <div
      class="notification"
      :class="{ 'is-primary': hover, clickable: hover }"
      @mouseover="hover = true"
      @mouseleave="hover = false"
    >
      <div class="media center">
        <div class="media-content">
          <div class="content">
            <p>
              <span class="title is-5">Some category</span>
              <br>
              <span class="subtitle is-7">15 questions</span>
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
export default {
  name: 'category-summary',
  data: function () {
    return {
      hover: false
    }
  }
}
</script>

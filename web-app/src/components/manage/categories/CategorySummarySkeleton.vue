<template>
  <div class="column is-half-tablet is-one-third-desktop">
    <div class="notification">
      <div class="media center">
        <div class="media-content">
          <div class="content">
            <p>
              <b-skeleton width="50%" active />
              <b-skeleton width="25%"/>
            </p>
          </div>
        </div>
        <div class="media-right">
          <figure class="image is-32x32">
            <b-skeleton width="32px" height="32px" />
          </figure>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'CategorySummarySkeleton'
}
</script>

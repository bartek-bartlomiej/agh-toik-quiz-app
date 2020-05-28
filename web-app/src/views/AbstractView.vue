<template>
  <div>
    <navigation />
    <div class="section">
      <div class="container is-widescreen">
        <router-view />
      </div>
    </div>
  </div>
</template>

<script>
import Navigation from '@/components/Navigation'

export default {
  name: 'AbstractView',
  components: { Navigation }
}
</script>

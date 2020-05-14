<template>
  <div>
    <navigation/>
    <router-view/>
  </div>
</template>
<script>
import Navigation from './components/Navigation'

export default {
  components: { Navigation }
}
</script>

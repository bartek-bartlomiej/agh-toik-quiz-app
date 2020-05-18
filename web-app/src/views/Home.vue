<template>
  <div>
    <section class="hero is-medium is-white is-bold">
      <div class="hero-body">
        <figure class="image center">
          <img id="logo" src="../assets/logo.svg" alt="Quiz! logo">
        </figure>
      </div>
    </section>
    <HelloWorld msg="Welcome to Your Vue.js App"/>
  </div>
</template>

<script>
// @ is an alias to /src
import HelloWorld from '@/components/HelloWorld.vue'

export default {
  name: 'Home',
  components: {
    HelloWorld
  }
}
</script>

<style>
  #logo {
    max-width: 520px;
  }
</style>

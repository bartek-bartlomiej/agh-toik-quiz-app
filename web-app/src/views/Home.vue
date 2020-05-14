<template>
  <div>
    <section class="hero is-primary">
      <div class="hero-body">
        <figure class="image center">
          <img id="logo" alt="Vue logo" src="../assets/logo.png">
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
    max-width: 128px;
  }

  .center {
    display: flex;
    align-items: center;
    justify-content: center;
  }
</style>

<template>
  <section class="hero is-fullheight is-white is-bold">
    <div class="hero-head">
      <navigation/>
    </div>
    <div class="hero-body">
      <div class="container is-widescreen">
        <div class="columns is-centered">
          <div class="column is-two-thirds">
            <div class="content">
              <figure class="image is-centered">
                <img id="logo" src="@/assets/logo.svg" alt="Quiz! logo">
              </figure>
              <quiz-form/>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
import Navigation from '@/components/Navigation'
import QuizForm from '@/components/home/HomeNewQuizForm'

export default {
  name: 'Home',
  components: { QuizForm, Navigation }
}
</script>

<style>
  #logo {
    max-width: 520px;
  }
</style>

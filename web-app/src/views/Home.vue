<template>
  <div class="hero is-fullheight is-white is-bold">
    <div class="hero-head">
      <navigation />
    </div>
    <div class="hero-body">
      <div class="container is-widescreen">
        <div class="columns is-centered">
          <div class="column is-two-thirds-tablet is-half-desktop">
            <div class="content">
              <figure class="image is-centered">
                <img id="logo" src="@/assets/logo.svg" alt="Quiz! logo" />
              </figure>
              <quiz-form />
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import Navigation from '@/components/Navigation'
import QuizForm from '@/components/home/NewQuizForm'

export default {
  name: 'Home',
  components: { Navigation, QuizForm }
}
</script>

<style>
  #logo {
    max-width: 420px;
  }
</style>

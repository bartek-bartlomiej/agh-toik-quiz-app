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
                <img id="logo" src="../assets/logo.svg" alt="Quiz! logo">
              </figure>

              <form>
                <section class="section box">
                  <b-field horizontal label="Category" custom-class="is-large">
                    <b-select placeholder="Select a category" size="is-large">
                      <option
                        v-for="(option, index) in [1,2,3]"
                        :value="option"
                        :key="index">
                        {{ option }}
                      </option>
                    </b-select>
                  </b-field>

                  <b-field horizontal label="Difficulty" custom-class="is-large">
                    <div class="buttons has-addons">
                      <b-button size="is-large">Easy</b-button>
                      <b-button size="is-large" type="is-primary" class="is-selected">Medium</b-button>
                      <b-button size="is-large">Hard</b-button>
                    </div>
                  </b-field>

                  <b-field horizontal label="Quantity" custom-class="is-large">
                    <b-numberinput
                      min="0"
                      max="100"
                      icon-pack="fa"
                      controls-position="compact"
                      size="is-large" />
                  </b-field>
                </section>
                <div class="buttons is-centered">
                  <b-button type="is-primary" size="is-large">Start Quiz!</b-button>
                  <b-button type="is-white">Manage questions</b-button>
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
// @ is an alias to /src
import Navigation from '@/components/Navigation'

// Difficulty:
//   type: string
// enum:
// - hard
// - medium
// - easy
// default: medium

export default {
  name: 'Home',
  components: {
    Navigation
  }
}
</script>

<style>
  #logo {
    max-width: 520px;
  }
</style>

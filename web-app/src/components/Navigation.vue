<template>
    <b-navbar
      fixed-top
      shadow
      spaced
      wrapper-class="container is-widescreen"
    >
      <template slot="brand">
        <b-navbar-item tag="router-link" :to="{ path: '/' }">
          <img src="../assets/logo.svg" alt="Quiz! Logo">
        </b-navbar-item>
      </template>

      <template slot="start">
        <b-navbar-item tag="router-link" :to="{ path: '/' }">
          Start Quiz!
        </b-navbar-item>
      </template>

      <template slot="end">
        <b-navbar-item tag="router-link" :to="{ path: '/About'}" >
          <span class="icon">
            <b-icon pack="fa" icon="edit" size="is-small" />
          </span>
          <span>Manage data</span>
        </b-navbar-item>
        <b-navbar-dropdown label="Info">
          <b-navbar-item
            href="https://github.com/bartek-bartlomiej/agh-toik-quiz-app"
            target="_blank"
          >
            <span class="icon">
              <b-icon pack="fab" icon="github" size="is-small"/>
            </span>
            <span>Github</span>
          </b-navbar-item>
        </b-navbar-dropdown>
      </template>
    </b-navbar>
</template>

<script>
export default {
  name: 'navigation'
}
</script>

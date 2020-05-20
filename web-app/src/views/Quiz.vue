<template>
  <div class="section">
    <div class="container is-widescreen">
      <div class="columns is-centered">
        <div class="column is-two-thirds-tablet is-half-desktop">
          <nav class="level">
            <div class="level-item">
              <h1 class="title is-4 is-spaced">Question 1 ouf of 10</h1>
            </div>
          </nav>
          <section class="section">
            <h2 class="subtitle has-text-centered">Who is the most powerful hero in the world?</h2>
            <div class="buttons">
              <template v-for="(answer, index) in ['Thanos', 'Shaggy', 'Superman', 'Saitama']">
                <b-button
                  :key="index"
                  expanded
                  outlined
                  size="is-large"
                  type="is-primary">{{ answer }}
                </b-button>
              </template>
            </div>
            <nav class="level">
              <div class="level-left"/>
              <div class="level-right">
                <div class="level-item">
                  <b-button
                    disabled
                    type="is-primary">
                    Next
                  </b-button>
                </div>
              </div>
            </nav>
          </section>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Quiz'
}
</script>

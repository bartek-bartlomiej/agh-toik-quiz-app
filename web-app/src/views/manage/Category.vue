<template>
  <div class="section">
    <div class="container is-widescreen">
      <nav class="level">
        <div class="level-left">
          <div class="level-item">
            <h1 class="title">Category {{ id }}</h1>
          </div>
        </div>
        <div class="level-right">
          <div class="level-item">
            <b-button type="is-danger" outlined>Delete</b-button>
          </div>
          <div class="level-item">
            <b-button type="is-primary" outlined>Edit</b-button>
          </div>
          <div class="level-item">
            <b-button type="is-primary">Add question</b-button>
          </div>
        </div>
      </nav>
        <b-table
          :data="data"
          striped
          hoverable>
          <template slot-scope="props">
            <b-table-column field="id" label="ID" width="4%" centered numeric>
              {{ props.row.id }}
            </b-table-column>

            <b-table-column field="question" label="Question" width="32%">
              {{ props.row.question }}
            </b-table-column>

            <b-table-column field="answer" label="Answer" width="32%">
              {{ props.row.answer }}
            </b-table-column>

            <b-table-column field="difficulty" label="Difficulty" width="32%" centered>
              <span :class="['tag', difficultyColor(props.row.difficulty)]">
                  {{ capitalize(props.row.difficulty) }}
              </span>
            </b-table-column>
          </template>
          <template slot="empty">
            <section class="section">
              <div class="content has-text-grey has-text-centered">
                <p>
                  <b-icon
                    pack="fa"
                    icon="pencil-ruler"
                    size="is-large">
                  </b-icon>
                </p>
                <p>Nothing here.</p>
                <a>Add new question</a>
              </div>
            </section>
          </template>
        </b-table>
    </div>
  </div>
</template>

<script>
const colors = {
  easy: 'is-success',
  medium: 'is-warning',
  hard: 'is-danger'
}

export default {
  name: 'Category',
  props: {
    id: {
      type: Number,
      required: true
    }
  },
  data () {
    return {
      data: [
        { id: 1, difficulty: 'easy', question: '2+2=?', answer: '4' },
        { id: 2, difficulty: 'medium', question: 'Best hero', answer: 'Shaggy' },
        { id: 3, difficulty: 'hard', question: 'Ala has...', answer: 'cat' },
        { id: 4, difficulty: 'hard', question: 'Q1', answer: 'A1' },
        { id: 5, difficulty: 'medium', question: 'Q2', answer: 'A2' }
      ]
    }
  },
  methods: {
    capitalize ([initial, ...rest]) {
      return [initial.toUpperCase(), ...rest].join('')
    },
    difficultyColor (difficulty) {
      return colors[difficulty]
    }
  }
}
</script>

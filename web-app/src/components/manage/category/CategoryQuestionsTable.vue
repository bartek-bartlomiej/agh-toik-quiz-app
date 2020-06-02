<template>
  <b-table
    :data="questionSummaries"
    :loading="loading"
    striped
    hoverable>
    <template slot-scope="props">
      <b-table-column field="id" label="No." width="4%" centered numeric>
        {{ props.row.no }}
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
</template>

<script>
import { loadingMixin } from '@/mixin/loadingMixin'

const colors = {
  easy: 'is-success',
  medium: 'is-warning',
  hard: 'is-danger'
}

const loadingQuestionsMixin = loadingMixin({
  operationName: 'getQuestions',
  consoleError: 'Could not get questions',
  toastMessage: 'Could not display questions',
  shouldRetry: true
})

export default {
  name: 'CategoryQuestionsTable',
  mixins: [loadingQuestionsMixin],
  props: {
    categoryId: {
      type: Number,
      required: true
    }
  },
  computed: {
    questions () {
      return this.externalData !== undefined ? this.externalData : []
    },
    questionSummaries () {
      return this.questions.map((question, index) => ({
        no: index + 1,
        difficulty: question.difficulty,
        question: question.body,
        answer: question.answers[question.correctAnswer]
      }))
    }
  },
  methods: {
    difficultyColor (difficulty) {
      return colors[difficulty]
    },
    capitalize ([initial, ...rest]) {
      return [initial.toUpperCase(), ...rest].join('')
    }
  },
  created () {
    this.loadData({
      category: this.categoryId
    })
  }
}
</script>

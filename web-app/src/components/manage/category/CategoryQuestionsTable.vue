<template>
  <b-table
    :data="questionSummaries"
    :loading="pending"
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
        <span
          class="is-capitalized"
          :class="['tag', difficultyColor(props.row.difficulty)]"
        >
            {{ props.row.difficulty }}
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
import apiOperationMixin from '@/mixin/apiOperationMixin'

const colors = {
  easy: 'is-success',
  medium: 'is-warning',
  hard: 'is-danger'
}

const mixinData = {
  operationName: 'getQuestions',
  shouldRetry: true,
  consoleErrorMessage: 'Could not get questions',
  toastErrorMessage: 'Could not display questions…'
}

export default {
  name: 'CategoryQuestionsTable',
  mixins: [apiOperationMixin],
  props: {
    categoryId: {
      type: Number,
      required: true
    }
  },
  data: function () {
    return {
      questions: [],
      ...mixinData
    }
  },
  computed: {
    operationParams () {
      return {
        category: this.categoryId
      }
    },
    questionSummaries () {
      return this.questions.map(({ answers, body, correctAnswer, difficulty }, index) => ({
        no: index + 1,
        difficulty: difficulty,
        question: body,
        answer: answers[correctAnswer]
      }))
    }
  },
  methods: {
    handleOperationSucceeded (questions) {
      this.questions = questions
    },
    difficultyColor (difficulty) {
      return colors[difficulty]
    }
  },
  created () {
    this.performOperation()
  }
}
</script>

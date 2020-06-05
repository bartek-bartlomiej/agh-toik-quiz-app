<template>
  <b-table
    :data="questionSummaries"
    :loading="pending"
    striped
    hoverable
    :row-class="() => 'clickable'"
    @click="handleSelectQuestion">
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
          <a @click="$emit('show-add-modal-requested')">Add new question</a>
        </div>
      </section>
    </template>
  </b-table>
</template>

<script>
import apiOperationMixin from '@/mixin/apiOperationMixin'
import { Question } from '../../../api/model'

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
    },
    questions: {
      type: Array
    }
  },
  data: function () {
    return {
      $questions: this.questions,
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
      return this.$data.$questions.map(({ id, answers, body, correctAnswer, difficulty }, index) => ({
        id: id,
        no: index + 1,
        difficulty: difficulty,
        question: body,
        answer: answers[correctAnswer]
      }))
    }
  },
  watch: {
    questions (newQuestions) {
      this.$data.$questions = newQuestions
    },
    '$data.$questions' (newQuestions) {
      this.$emit('update:questions', newQuestions)
    }
  },
  methods: {
    handleOperationSucceeded (questions) {
      this.$data.$questions = questions
    },
    difficultyColor (difficulty) {
      return colors[difficulty]
    },
    handleSelectQuestion (row) {
      const question = [this.$data.$questions.find(question => question.id === row.id)]
        .map(data => new Question(
          data.id,
          data.body,
          data.answers,
          data.correctAnswer,
          data.category.id,
          data.difficulty))[0]
      this.$emit('show-edit-modal-requested', question)
    }
  },
  created () {
    this.performOperation()
  }
}
</script>

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
        <b-table :data="data" :columns="columns">
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
export default {
  name: 'Category',
  props: {
    id: Number
  },
  data () {
    return {
      data: [
        { id: 1, difficulty: 'Easy', question: '2+2=?', answer: '4' },
        { id: 2, difficulty: 'Medium', question: 'Best hero', answer: 'Shaggy' },
        { id: 3, difficulty: 'Hard', question: 'Ala has...', answer: 'cat' },
        { id: 4, difficulty: 'Hard', question: 'Q1', answer: 'A1' },
        { id: 5, difficulty: 'Medium', question: 'Q2', answer: 'A2' }
      ],
      columns: [
        {
          field: 'id',
          label: 'ID',
          width: '4%',
          centered: true,
          numeric: true
        },
        {
          field: 'question',
          label: 'Question',
          width: '32%'
        },
        {
          field: 'answer',
          label: 'Answer',
          width: '32%'
        },
        {
          field: 'difficulty',
          label: 'Difficulty',
          width: '32%'
        }
      ]
    }
  }
}
</script>

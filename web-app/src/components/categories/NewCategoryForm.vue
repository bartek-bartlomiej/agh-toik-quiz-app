<template>
  <form @submit.prevent="handleSubmit">
    <div class="modal-card">
      <header class="modal-card-head">
        <p class="modal-card-title">New category</p>
      </header>
      <section class="modal-card-body">
        <b-field label="Name">
          <b-input
            type="text"
            v-model="name"
            placeholder="Unique name category"
            validation-message="Name cannot be empty"
            required>
          </b-input>
        </b-field>
      </section>
      <footer class="modal-card-foot">
        <b-button type="is-primary" native-type="submit">Add category</b-button>
        <b-button @click="$parent.close()">Cancel</b-button>
      </footer>
    </div>
  </form>
</template>

<script>
import client from '../../api'

export default {
  name: 'new-category-form',
  data: function () {
    return {
      name: ''
    }
  },
  methods: {
    handleSubmit () {
      // have to add Content-Type header manually due to OpenApi-client-axios behaviour
      client.addCategory(null, this.name, { headers: { 'Content-Type': 'application/json' } })
        .then(response => {
          console.log(response.data)

          this.showToast('success')
          this.$emit('category-added', response.data)
          this.$parent.close()
        })
        .catch(error => {
          console.error('Adding category failed: ' + error.toString())

          this.showToast('error')
        })
    },
    showToast (type) {
      const messages = {
        success: 'Category added successfully',
        error: 'Could not add category. Please, try again.'
      }
      const types = {
        success: 'is-success',
        error: 'is-warning'
      }
      this.$buefy.toast.open({
        duration: 3000,
        message: messages[type],
        position: 'is-bottom',
        type: types[type]
      })
    }
  }
}
</script>

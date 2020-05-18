<template>
  <form action="">
    <div class="modal-card" style="width: auto">
      <header class="modal-card-head">
        <p class="modal-card-title">New category</p>
      </header>
      <section class="modal-card-body">
        <b-field label="Name">
          <b-input
            type="text"
            placeholder="Unique name category"
            required>
          </b-input>
        </b-field>
      </section>
      <footer class="modal-card-foot">
        <b-button type="is-primary">Add category</b-button>
        <b-button @click="$parent.close()">Cancel</b-button>
      </footer>
    </div>
  </form>
</template>

<script>
export default {
  name: 'new-category-form'
}
</script>

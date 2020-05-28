<template>
  <div class="hello">
    <div class="wrapper">
      <div class="left">
        <img src="./../assets/bookly.jpg">
      </div>
      <div class="right">
        <div class="card">
          <input v-model="invite_code" placeholder="Code">
          <br>
          <button v-on:click="editPage" class="btn btn-primary">Go!</button>
        </div>
      </div>
    </div>
    <div style="clear: both"></div>
    <div class="separator" style="margin-bottom: 16px"></div>
    <h1>{{ hellomsg }}</h1>
    <h3>Do you still remember the friendship book from your childhood days?
      <br>
      Whoever had the most pages and the greatest entries was highly regarded.
      <br>
      It was handcrafted, painted, drawn and designed.</h3>
    <br>
    <h4>As books become less important with digitalization,
      <br>
      we want to maintain the old charm of friendship books and bring back the fun of designing and collecting.
    </h4>
  </div>
</template>

<script>
export default {
  name: 'hello',
  props: { hellomsg: { type: String, required: true } },

  data() {
    return {
      invite_code: ""
    }
  },
  methods: {
    editPage() {
      this.$router.push('/pageeditor?id=' + this.invite_code);
    }
  }
}

</script>

<style scoped>
  img {
    width: 100%;
  }
  input {
    width: 80%;
  }
  button {
    width: 80%;
  }
  .card {
    align-items: center;
    padding: 32px;
    margin: 20% 20%;
  }
  .separator {
    width: 100%;
    height: 2px;
    background: black;
  }
  .left {
    width: 50%;
    float: left;
  }
  .right {
    width: 50%;
    float: right;
    padding: 64px;
    align-items: center;
  }
</style>
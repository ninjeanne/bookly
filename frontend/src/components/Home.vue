<template>
  <div class="main">
    <div>
      <div class="top">
        <img class="card" src="../assets/bookly.jpg"/>
      </div>
      <div class="container">
        <div class="left">
          <div class="card">
          <div style="font-weight: bolder; font-size: xx-large">Welcome!</div>
            <br>
          <div class="container">
            <div style="font-size: large">
              <p>
              Do you still remember the friendship book from your childhood days?
              Whoever had the most pages and the greatest entries was highly regarded.
              It was handcrafted, painted, drawn and designed.
              </p>
              <p>
              As books become less important with digitalization,
              we want to maintain the old charm of friendship books and bring back the fun of designing and collecting.
              </p>
            </div>
          </div>
          </div>
          <div class="card">
            <router-link class="btn btn-dark" to="/book">Go to <b>YOUR</b> Book</router-link>
          </div>
        </div>
        <div class="right">
          <img src="../assets/logo.png" class="card"/>
          <div class="card">
            <p style="font-size: large">Want to style a page for a friend? Enter your page edit <b>CODE</b> here.</p>
            <input type="text" v-model="invite_code">
            <button v-on:click="editPage" class="btn btn-dark">Go!</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  export default {
    name: "hello",
    props: {
      hellomsg: {
        type: String,
        required: true
      }
    },

    data() {
      return {
        invite_code: ""
      };
    },
    methods: {
      editPage() {
        this.$router.push("/pageeditor?id=" + this.invite_code);
      }
    }
  };

</script>

<style scoped>
  .btn {
    width: 100%;
    margin-top: 16px;
  }
  .card {
    margin-top: 16px;
    padding: 16px;
    width: 100%;
  }
  .container {
    width: 100%;
  }
  .left {
    width: 48%;
    float: left;
    margin-right: 2%;
  }
  .right {
    width: 48%;
    float: right;
    margin-left: 2%;
  }
  .main {
    padding: 32px;
    margin-left: 18%;
    margin-right: 18%;
  }
</style>
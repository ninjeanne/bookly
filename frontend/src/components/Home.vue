<template>
  <div class="hero-image">
    <div class="hero-text">
      <div class="panel panel-default infotext">
        <div class="panel-heading" style="font-weight: bolder; font-size: x-large">Welcome!</div>
        <div class="panel-body">
          <div style="float: left; width: 50%;">
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
          <img src="../assets/logo.png" class="logo" alt="Logo" style="float: right; width: 50%;" />
        </div>
      </div>
      <hr>
      <div class="book btn btn-dark">
        <router-link to="/book" style="color:white !important;">
          Go to <b>YOUR</b> Book
        </router-link>
      </div>
      <div class="edit infotext">
        <p>Want to style a page for a friend? Enter your page edit <b>CODE</b> here.</p>
        <input type="text" v-model="invite_code" maxlength="30" style="margin-right: 10px;" placeholder="this:is:an:example" class="code">
        <button v-on:click="editPage" class="code btn btn-dark" style="width:17%">Go!</button>
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
  img {
    width: 100%;
  }

  input {
    width: 80%;
  }

  button {
    width: 80%;
  }

  body, html {
    height: 100%;

  }

  /* The hero image */
  .hero-image {
    /* Use "linear-gradient" to add a darken background effect to the image (photographer.jpg). This will make the text easier to read */
    background-image: linear-gradient(rgba(0, 0, 0, 0.2), rgba(0, 0, 0, 0.2)), url("./../assets/bookly.jpg");

    /* Set a specific height */
    height: 50%;

    /* Position and center the image to scale nicely on all screens */
    background-position: center;
    background-repeat: no-repeat;
    background-size: cover;
    position: relative;
  }

  /* Place text in the middle of the image */
  .hero-text {
    text-align: justify;
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    font-weight: bold;
  }

  .logo {
    width: 20%;
    background: white;
    display: block;
    margin-left: auto;
    margin-right: auto;
  }

  .panel-body {
    text-align: justify;
  }

  .code {
    width: 70%;
  }

  .infotext {
    background-color: white;
    padding: 40px;
    opacity: 97%;
  }

  .panel {
    height: 400px;
  }

  .book:hover, .book {
    float: left;
    width: 46%;
    height: 120px;
    text-align: center;
    vertical-align: middle;
    line-height: 90px;
    font-size: xx-large;
  }

  .edit {
    float: right;
    width: 53%;
    padding: 10px;
    height: 120px;
    text-align: center;
  }

</style>

<template>
  <div class="hero-image">
    <div class="hero-text">
      <div class="panel panel-default">
        <div class="panel-body">
          <img src="../assets/logo.png" class="logo" alt="Logo" />
        </div>
      </div>
      <hr>
      <div class="infotext">
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
      <hr>
      <div class="infotext">
        <div class="center">
          <router-link to="/book" class="btn btn-dark" style="margin-bottom: 15px; color:white !important;">
            Go to YOUR Book
          </router-link>
        </div>
        <p style="text-align: center;">OR</p>
        <div class="center">
          <p>Want to style a page for a friend? Enter your page edit <b>CODE</b> here.</p>
          <input type="text" v-model="invite_code" maxlength="30" style="margin-right: 10px;" placeholder="this:is:an:example" class="code">
          <button v-on:click="editPage" class="code btn btn-dark" style="width:15%">Go!</button>
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

  .center {
    text-align: center;
  }

  .code {
    width: 45%;
  }

  .infotext {
    background-color: white;
    padding: 40px;
  }


</style>

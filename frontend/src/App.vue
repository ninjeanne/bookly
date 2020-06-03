<template>
  <div id="app">
    <div v-if="notHomePage()">
      <b-card-header header-tag="nav">
        <b-nav card-header tabs align="center">
          <b-nav-item to="/" exact exact-active-class="active">Home</b-nav-item>
          <b-nav-item to="/book" exact exact-active-class="active">Book</b-nav-item>
          <b-nav-item to="/user" exact exact-active-class="active">Profile</b-nav-item>
        </b-nav>
      </b-card-header>
    </div>
    <router-view id="main" :hellomsg="msg"></router-view>
    <!-- Footer -->
    <footer class="page-footer font-small indigo">
      <div class="container">
        <div class="row text-center d-flex justify-content-center pt-5 mb-3">
          <div class="col-md-2 mb-3">
            <h6 class="text-uppercase font-weight-bold">
              <router-link to="/help">Help</router-link>
            </h6>
          </div>
          <div class="col-md-2 mb-3">
            <h6 class="text-uppercase font-weight-bold">
              <router-link to="/about">About us</router-link>
            </h6>
          </div>
          <div class="col-md-2 mb-3">
            <h6 class="text-uppercase font-weight-bold">
              <router-link to="/termsofservice">Terms of Service</router-link>
            </h6>
          </div>
          <div class="col-md-2 mb-3">
            <h6 class="text-uppercase font-weight-bold">
              <a href="https://gitlab.com/project_bookly/bookly">GitLab</a>
            </h6>
          </div>
          <div class="col-md-2 mb-3">
            <h6 class="text-uppercase font-weight-bold">
              <a href="https://blog.bookly.online">Blog</a>
            </h6>
          </div>
          <div class="col-md-2 mb-3">
            <h6 class="text-uppercase font-weight-bold">
              <router-link to="https://sonarqube.bookly.online">SonarQube</router-link>
            </h6>
          </div>
        </div>
        <hr class="rgba-white-light" style="margin: 0 15%;">
        <div class="row d-flex text-center justify-content-center mb-md-0 mb-4">
          <div class="col-md-8 col-12 mt-5">
            <p style="line-height: 1.7rem">This website is a product of countless unhealthy energy drinks and some GitFlow</p>
          </div>
        </div>
      </div>
      <div class="footer-copyright text-center py-3">© 2020 Copyright:
        <a href="https://bookly.online"> bookly.online</a>
      </div>
    </footer>
    <!-- Footer -->
  </div>
</template>

<script>

  export default {
    name: "app",
    data() {
      return {
        msg: "Welcome to bookly!"
      };
    },
    methods: {
      notHomePage() {
        return document.location.pathname !== "/";
      }
    }
  };

</script>

<style lang="scss">
  #main {
    min-height: 100vh;
  }

  #app {
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #8B4513;
    padding-bottom: 16px;
  }

  a:hover {
    color: #CD853F !important;
    text-decoration: none !important;
  }

  a {
    color: #800000 !important;
    text-decoration: none !important;
  }
</style>

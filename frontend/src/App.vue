<template>
  <div id="app">
    <div>
      <b-card-header header-tag="nav">
        <b-nav card-header tabs align="center">
          <b-nav-item to="/" exact exact-active-class="active">Home</b-nav-item>
          <b-nav-item to="/login" exact exact-active-class="active">Login</b-nav-item>
          <b-nav-item to="user" exact exact-active-class="active">Profile</b-nav-item>
        </b-nav>
      </b-card-header>
    </div>
    <router-view :hellomsg="msg"></router-view>
  </div>
</template>

<script>

export default {
  name: 'app',
  data () {
    return {
      msg: 'Welcome to bookly!'
    }
  }
}
</script>

<style lang="scss">
  #app {
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #2c3e50;
    padding-bottom: 16px;
  }
</style>
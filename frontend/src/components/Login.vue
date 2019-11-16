<template>
  <div class="unprotected" v-if="loginError">
    <h5><b-badge variant="danger">Login failed</b-badge></h5>
  </div>
  <div class="unprotected" v-else>
    <form @submit.prevent="callLogin()">
      <input type="text" placeholder="username" v-model="user">
      <br>
      <input type="password" placeholder="password" v-model="password">
      <br>
      <b-btn variant="success" type="submit">Login</b-btn>
    </form>
  </div>

</template>

<script>
export default {
  name: 'login',

  data () {
    return {
      loginError: false,
      user: '',
      password: '',
      error: false,
      errors: []
    }
  },
  methods: {
    callLogin() {
      this.errors = [];
      this.$store.dispatch("login", { user: this.user, password: this.password})
              .then(() => {
                this.$router.push('/Home')
              })
              .catch(error => {
                this.loginError = true;
                this.errors.push(error);
                this.error = true
              })
    }
  }
}
</script>
<style scoped>
  input {
    padding: 16px;
    min-width: 300px;
  }
  button {
    margin-top: 16px;
    min-width: 300px;
  }
</style>
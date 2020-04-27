<template>
  <div>
    <h2>Hey {{ username }}!!</h2>
    <button v-on:click="logout">Logout</button>
  </div>
</template>

<script>
  import keycloak from './../main';

  export default {
    name: 'user',

    beforeMount() {
      console.log(localStorage.getItem("userInfo"));
      this.username = JSON.parse(localStorage.getItem('userInfo')).preferred_username;
    },
    data() {
      return {
        username: ""
      }
    },
    methods: {
      logout: function () {
        this.$router.push("/");
        keycloak.logout();
      }
    }
  }

</script>

<style scoped>
  div {
    padding: 32px;
  }
</style>

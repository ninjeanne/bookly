<template>
  <div>
    <h2>Hey {{ username }}!!</h2>
    <button v-on:click="logout">Logout</button>
  </div>
</template>

<script>
  import store from './../store';
  import keycloak from './../main';

  export default {
    name: 'user',

    beforeMount() {
      this.username = store.getters.getUserName;
      if(this.username == null) {
        this.getUser();
      }
    },
    data () {
      return {
        username: ""
      }
    },
    methods: {
      getUser() {
        console.log(process.env.VUE_APP_BACKEND);
        let user = JSON.parse(localStorage.getItem('userInfo'));
        this.username = user.preferred_username;
      },
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

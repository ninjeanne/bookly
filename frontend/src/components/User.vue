<template>
  <div>
    <h2>Hey {{ username }}!!</h2>
    <button v-on:click="logout">Logout</button>
    <button v-on:click="invite">Invite Friend</button>
    <h2>Code: {{ invite_code }} </h2>
  </div>
</template>

<script>
  import keycloak from './../main';

  export default {
    name: 'user',

    beforeMount() {
      this.username = JSON.parse(localStorage.getItem('userInfo')).preferred_username;
    },
    data() {
      return {
        username: "",
        invite_code: ""
      }
    },
    methods: {
      logout: function () {
        this.$router.push("/");
        keycloak.logout();
      },
      invite: function () {
        this.$store.dispatch("newPage")
                .then((response) => {
                  this.invite_code = response.data.uuid;
                })
      }
    }
  }

</script>

<style scoped>
  div {
    padding: 32px;
  }
</style>

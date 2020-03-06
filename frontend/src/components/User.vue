<template>
  <div v-if="isLoggedIn" class="user">
    <h2>Hey {{ username }}!!</h2>
  </div>
  <div v-else>
    <h2>You must be logged in to view your profile</h2>
    <a href="/login">Click here to login!</a>
  </div>
</template>

<script>
  import store from './../store';

  export default {
    name: 'user',

    beforeMount() {
      this.username = store.getters.getUserName;
      if(this.username == null) {
        this.getUser();
      } else {
        this.isLoggedIn = true;
      }
    },
    data () {
      return {
        isLoggedIn: true,
        username: ""
      }
    },
    methods: {
      getUser() {
        this.$store.dispatch("user")
            .then((response) => {
              console.log(response);
              this.username = response.data.username;
            })
        console.log("called")
      }
    }
  }

</script>

<style scoped>
  div {
    padding: 32px;
  }
</style>

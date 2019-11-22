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
  import api from "./backend-api";
  import store from './../store';

  export default {
    name: 'user',

    beforeMount() {
      this.username = store.getters.getUserName;
      this.password = store.getters.getUserPass;
      this.isLoggedIn = store.getters.isLoggedIn;
    },

    data () {
      return {
        response: [],
        errors: [],
        user: {
          lastName: '',
          firstName: '',
          id: 0
        },
        username: '',
        password: '',
        showResponse: false,
        retrievedUser: {},
        showRetrievedUser: false,
        isLoggedIn: false
      }
    },
    methods: {
      // Fetches posts when the component is created.
      retrieveUser () {
        api.getUser(this.user.id).then(response => {
            // JSON responses are automatically parsed.
            this.retrievedUser = response.data;
            this.showRetrievedUser = true
          })
          .catch(e => {
            this.errors.push(e)
          })
      },
    }
  }

</script>

<style scoped>
  div {
    padding: 32px;
  }
</style>

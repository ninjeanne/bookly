<template>
  <div class="main">
    <div class="wrapper">
      <div class="left">
        <div class="card">
          <h2>Username: {{ username }}</h2>
          <h3>E-Mail: {{ email }}</h3>
          <h3>First Name: {{ firstName }}</h3>
          <h3>Last Name: {{ lastName }}</h3>
          <button class="btn btn-info" v-on:click="saveUser">Save</button>
          <button class="btn btn-info" v-on:click="logout">Logout</button>
        </div>
        <div class="card">
          <h2>Code: {{ invite_code }} </h2>
          <button class="btn btn-info" v-on:click="invite">Invite Friend</button>
        </div>
      </div>
      <div class="right">
        <div class="card">
          <button class="btn btn-info" v-on:click="share">Share my book</button>
        </div>
        <div class="card">
          <b-list-group>
            <b-list-group-item v-for="page in pagesDisplay"> {{ page }}</b-list-group-item>
            <button class="btn btn-danger" v-on:click="deletePage">Delete selected page</button>
          </b-list-group>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import keycloak from './../main';

  export default {
    name: 'user',

    beforeMount() {
      this.getUser();
      this.getPages();
    },
    data() {
      return {
        username: "",
        email: "",
        firstName: "",
        lastName: "",
        invite_code: "",
        pagesDisplay: [],
        pagesUUID: []
      }
    },
    methods: {
      getUser() {
        this.$store.dispatch("getUser")
                .then((response) => {
                  this.username = response.data.username;
                  this.email = response.data.mail;
                  this.firstName = response.data.first_name;
                  this.lastName = response.data.last_name;
                })
      },
      getPages() {
        this.$store.dispatch("getPages")
                .then((response) => {
                  response.data.forEach(page => {
                    this.pagesDisplay.push("ID: " + page.uuid + ", Name: " + page.name);
                    this.pagesUUID.push(page.uuid);
                  });
                })
      },
      share() {
        // NYI
      },
      saveUser: function () {
        // NYI
      },
      deletePage: function () {
        // NYI
      },
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
  button {
    margin-top: 16px;
  }
  .main {
    padding: 32px;
  }
  .card {
    margin: 16px;
    padding: 16px;
  }
  .left {
    width: 50%;
    float: left;
  }
  .right {
    width: 50%;
    float: right;
  }
</style>

<template>
  <div class="main">
    <div class="wrapper">
      <div class="left">
        <div class="card">
          <div class="input-group mb-3">
            <div class="input-group-prepend">
              <span class="input-group-text">Username</span>
            </div>
            <input type="text" class="form-control" aria-label="Default"
                   aria-describedby="inputGroup-sizing-default" v-model="username" readonly>
          </div>
          <div class="input-group mb-3">
            <div class="input-group-prepend">
              <span class="input-group-text">E-Mail</span>
            </div>
            <input type="text" class="form-control" aria-label="Default"
                   aria-describedby="inputGroup-sizing-default" v-model="email">
          </div>
          <div class="input-group mb-3">
            <div class="input-group-prepend">
              <span class="input-group-text">First name</span>
            </div>
            <input type="text" class="form-control" aria-label="Default"
                   aria-describedby="inputGroup-sizing-default" v-model="firstName">
          </div>
          <div class="input-group mb-3">
            <div class="input-group-prepend">
              <span class="input-group-text">Last name</span>
            </div>
            <input type="text" class="form-control" aria-label="Default"
                   aria-describedby="inputGroup-sizing-default" v-model="lastName">
          </div>
          <button class="btn btn-info" v-on:click="saveUser">Save</button>
          <button class="btn btn-info" v-on:click="logout">Logout</button>
        </div>
        <div class="card">
          <h2>Code: {{ invite_code }} </h2>
          <button class="btn btn-info" v-on:click="invite">Invite friend</button>
        </div>
      </div>
      <div class="right">
        <div class="card">
          <button class="btn btn-info" v-on:click="share">Share my book</button>
        </div>
        <div class="card">
          <b-list-group>
            <div class="page" v-for="page in pagesUUID">
              <div class="left">
                <h4>{{ page }}</h4>
              </div>
              <div class="right">
                <button class="btn btn-danger" id="deletePage" v-on:click="deletePage(page)">Delete</button>
              </div>
            </div>
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
                    this.pagesUUID.push(page.uuid);
                  });
                })
      },
      share() {
        // NYI
      },
      saveUser: function () {
        this.$store.dispatch("updateUser",{username: this.username, email: this.email, firstName: this.firstName, lastName: this.lastName})
                .then((response) => { })
      },
      deletePage: function (uuid) {
        this.$store.dispatch("deletePage",{uuid: uuid})
                .then(() => {
                  function remove(array, element) {
                    return array.filter(el => el !== element);
                  }
                  const index = this.pagesUUID.indexOf(uuid);
                  if (index > -1) {
                    this.pagesUUID = remove(this.pagesUUID, uuid);
                  }
                })
      },

      logout: function () {
        this.$router.push("/");
        keycloak.logout();
      },
      invite: function () {
        this.$store.dispatch("newPage")
                .then((response) => {
                  this.invite_code = response.data.uuid;
                  this.pagesUUID.push(response.data.uuid);
                })
      }
    }
  }

</script>

<style scoped>
  button {
    margin-top: 16px;
  }
  .input-group-text {
    min-width: 120px;
  }
  .main {
    padding: 32px;
  }
  .card {
    margin: 16px;
    padding: 16px;
  }
  .page {
    width: 100%;
    height: 100%;
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
  #deletePage {
    margin: 0;
  }
</style>

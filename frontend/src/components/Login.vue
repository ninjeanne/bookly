<template>
    <div class="unprotected" v-if="loginError">
        <h5>
            <b-badge variant="danger">Login failed</b-badge>
        </h5>
    </div>
    <div class="unprotected" v-else-if="isNotLoggedIn">
        <form @submit.prevent="callLogin()">
            <input type="text" placeholder="username" v-model="user">
            <br>
            <input type="password" placeholder="password" v-model="password">
            <br>
            <b-btn variant="success" type="submit">Login</b-btn>
        </form>
        <router-link to="/register">Not registered yet?</router-link>
    </div>
    <div v-else>
        <h2>Looks like you're already logged in!</h2>
    </div>
</template>

<script>
    import store from './../store';

    export default {

        name: 'login',

        beforeMount() {
            this.isNotLoggedIn = !store.getters.isLoggedIn;
        },

        data() {
            return {
                isNotLoggedIn: false,
                loginError: false,
                user: '',
                password: '',
            }
        },
        methods: {
            callLogin() {
                this.errors = [];
                this.$store.dispatch("login", {user: this.user, password: this.password})
                    .then(() => {
                        this.$router.push('/home')
                    })
                    .catch(() => {
                        this.loginError = true;
                    })
            }
        }
    }
</script>
<style scoped>

    div {
        margin-top: 32px;
    }

    input {
        padding: 16px;
        min-width: 300px;
    }

    button {
        margin-top: 16px;
        min-width: 300px;
    }
</style>
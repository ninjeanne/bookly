<template>
    <div class="unprotected" v-if="!isLoggedIn">
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
            this.username = store.getters.getUserName;
            if(this.username == null) {
                this.getUser();
            } else {
                this.isLoggedIn = true;
            }
        },
        data() {
            return {
                user: "",
                isLoggedIn: false,
                password: "",
            }
        },
        methods: {
            callLogin() {
                this.$store.dispatch("login", {user: this.user, password: this.password})
                    .then(() => {
                        this.$router.push('/home')
                    })
            },
            getUser() {
                this.$store.dispatch("user")
                    .then(() => {
                        this.username = this.$store.getters.getUserName;
                        this.isLoggedIn = true;
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
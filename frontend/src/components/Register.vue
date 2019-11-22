<template>
    <div class="unprotected">
        <form @submit.prevent="callRegister()">
            <input type="text" placeholder="username" v-model="user">
            <br>
            <input type="text" placeholder="email" v-model="email">
            <br>
            <input type="password" placeholder="password" v-model="password1">
            <br>
            <input type="password" placeholder="password" v-model="password2">
            <br>
            <b-btn variant="success" type="submit">Register</b-btn>
        </form>
        <a href="/login">Already registered?</a>
    </div>
</template>

<script>
    export default {
        name: 'login',

        data () {
            return {
                user: '',
                email: '',
                password1: '',
                password2: '',
                error: false,
                errors: []
            }
        },
        methods: {
            callRegister() {
                this.errors = [];
                this.$store.dispatch("register", { user: this.user, email: this.email, password1: this.password1, password2: this.password2 })
                    .then(() => {
                        this.$router.push('/login')
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
    .unprotected {
        margin-top: 20px;
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
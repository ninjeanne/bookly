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
    export default {
        name: 'user',

        beforeMount() {
            this.isLoggedIn = this.$store.getters.isLoggedIn;
        },
        created() {
            if(!this.isLoggedIn) {
                this.getUser();
            }
        },
        data () {
            return {
                username: '',
                isLoggedIn: false
            }
        },
        methods: {
            getUser() {
                this.$store.dispatch("user")
                    .then(() => {
                        this.isLoggedIn = this.$store.getters.isLoggedIn;
                        this.username = this.$store.getters.getUserName;
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
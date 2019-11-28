<template>
    <div v-if="isLoggedIn" id="main">
        <div class="card card-cascade wider">
            <div class="view view-cascade overlay">
                <img class="card-img-top" src="./../assets/einstein.jpg" alt="Card image cap">
            </div>
            <div class="card-body card-body-cascade text-center">
                <h4 class="card-title"><strong>{{username}}'s Friendship Book</strong></h4>
                <h5 class="blue-text pb-2"><strong>I like pizza, but sometimes I go outside too</strong></h5>
                <p class="card-text">If you think you have the best Friendship Book, you haven't seen mine</p>
            </div>
            <div style="padding: 64px 64px 32px 64px">
                <b-btn style="float: left" type="button" class="btn btn-unique">Edit (!)</b-btn>
                <a style="float: right" href="#" class="btn btn-flat deep-purple-text p-1 mx-0 mb-0">Start Reading
                    (!)</a>
            </div>
        </div>
    </div>
    <div v-else>
        <h2>You must be logged in to view your book</h2>
        <a href="/login">Click here to login!</a>
    </div>
</template>

<script>
    import store from './../store';

    export default {
        name: "Book",
        beforeMount() {
            this.username = store.getters.getUserName;
            this.isLoggedIn = store.getters.isLoggedIn;
        },
        created() {
            if(!this.isLoggedIn) {
                this.getUser();
            }
        },
        data() {
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
    #main {
        margin: 32px 128px 256px 128px;
    }
</style>
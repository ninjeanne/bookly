<template>
    <div class="card">
        <div class="content">
            <img :src="image">
            <h4 class="card-title"><strong>{{ title }}</strong></h4>
        </div>
        <div style="padding: 64px 64px 32px 64px">
            <router-link style="float: left" to="/bookeditor" class="btn btn-flat deep-purple-text p-1 mx-0 mb-0">Edit</router-link>
            <router-link style="float: right" to="/page?0" class="btn btn-flat deep-purple-text p-1 mx-0 mb-0">Start Reading</router-link>
        </div>
    </div>
</template>

<script>
    import store from './../store';

    export default {
        name: "Book",

        beforeMount() {
            this.username = store.getters.getUserName;
            if(this.username == null) {
                this.getUser();
            }
            this.getBook();
            this.getCover();
        },
        data() {
            return {
                username: "",
                book: null,
                title: "",
                image: ""
            }
        },
        methods: {
            getUser() {
                this.$store.dispatch("user")
                    .then(() => {
                        this.username = this.$store.getters.getUserName;
                    })
            },
            getCover() {
                this.$store.dispatch("getBookCover")
                    .then((response) => {
                        this.image = 'data:image/jpeg;base64,'.concat(this.image.concat(response.data));
                    })
            },
            getBook() {
                this.$store.dispatch("getBook")
                    .then((response) => {
                        this.title = response.data.title;
                    })
            }
        }
    }
</script>

<style scoped>
    .card {
        max-width: 100%;
        min-width: 60%;
        max-height: 80%;
    }
    h4 {
        padding-top: 32px;
    }
    img {
        max-width: 100%;
    }
</style>

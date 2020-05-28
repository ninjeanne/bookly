<template>
    <div id="main">
        <div class="card">
            <div class="content">
                <div class="container">
                    <div class="top-left">
                        <img :src="sticker1">
                    </div>
                    <div class="top-right">
                        <img :src="sticker2">
                    </div>
                    <div class="mid">
                        <img :src="cover">
                    </div>
                    <div class="bot">
                        <h4 class="card-title"><strong>{{ title }}</strong></h4>
                        <h5 class="card-title"><strong>{{ subtitle }}</strong></h5>
                    </div>
                </div>
            </div>
            <div style="padding: 64px 64px 32px 64px">
                <router-link style="float: left" to="/bookeditor" class="btn btn-flat deep-purple-text p-1 mx-0 mb-0">Edit</router-link>
                <router-link style="float: right" to="/page?num=0" class="btn btn-flat deep-purple-text p-1 mx-0 mb-0">Start Reading</router-link>
            </div>
        </div>
    </div>
</template>

<script>

    export default {
        name: "Book",

        beforeMount() {
            this.getBook();
            this.getCover();
            this.getSticker1();
            this.getSticker2();
        },
        data() {
            return {
                title: "",
                subtitle: "",
                cover: "",
                sticker1: "",
                sticker2: ""
            }
        },
        methods: {
            getCover() {
                this.$store.dispatch("getBookCover")
                    .then((response) => {
                        this.cover = 'data:image/jpeg;base64,'.concat(this.cover.concat(response.data));
                    })
            },
            getSticker1() {
                this.$store.dispatch("getBookSticker", {number: "1"})
                    .then((response) => {
                        this.sticker1 = 'data:image/jpeg;base64,'.concat(this.sticker1.concat(response.data));
                    })
            },
            getSticker2() {
                this.$store.dispatch("getBookSticker", {number: "2"})
                    .then((response) => {
                        this.sticker2 = 'data:image/jpeg;base64,'.concat(this.sticker2.concat(response.data));
                    })
            },
            getBook() {
                this.$store.dispatch("getBook")
                    .then((response) => {
                        this.title = response.data.title;
                        this.subtitle = response.data.subtitle;
                    })
            }
        }
    }
</script>

<style scoped>
    #main {
        margin: 64px;
    }
    .card {
        max-width: 100%;
        min-width: 60%;
        max-height: 80%;
    }
    h4 {
        padding-top: 16px;
    }
    img {
        max-height: 30vh;
    }
    .container {
        display: grid;
        width: 100%;
        height: 100%;
        grid-template-areas: "top-left top-right"
        "mid mid"
        "bot bot"
        "bot bot";
        grid-template-columns: 1fr 1fr;
        grid-template-rows: 1fr 1fr 1fr 0.1fr;
    }
    .top-left {
        grid-area: top-left;
    }
    .top-right {
        grid-area: top-right;
    }
    .mid {
        grid-area: mid;
    }
    .bot {
        grid-area: bot;
    }
</style>

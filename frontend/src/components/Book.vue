<template>
    <div id="main">
        <div class="card">
            <div class="content">
                <div class="container-0" v-if="theme === 0">
                    <div class="top-left-0">
                        <img :src="sticker1">
                    </div>
                    <div class="top-right-0">
                        <img :src="sticker2">
                    </div>
                    <div class="mid-0">
                        <img :src="cover">
                    </div>
                    <div class="bot-0">
                        <h4 class="card-title"><strong>{{ title }}</strong></h4>
                        <h5 class="card-title"><strong>{{ subtitle }}</strong></h5>
                    </div>
                </div>
                <div class="container-1" v-else-if="theme === 1">
                    <div class="top-1">
                        <img :src="cover">
                    </div>
                    <div class="mid-left-1">
                        <img :src="sticker1">
                    </div>
                    <div class="mid-right-1">
                        <img :src="sticker2">
                    </div>
                    <div class="bot-1">
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
                theme: 0,
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
                        this.setField(this.title, response.data.title);
                        this.setField(this.subtitle, response.data.subtitle);
                        this.setField(this.theme, response.data.theme);
                    })
            },
            setField(textfield, value) {
                if(value === "null") {
                    textfield = "";
                } else {
                    textfield = value;
                }
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
    /* Theme 0 */
    .container-0 {
        display: grid;
        width: 100%;
        height: 100%;
        grid-template-areas: "top-left-0 top-right-0"
        "mid-0 mid-0"
        "bot-0 bot-0"
        "bot-0 bot-0";
        grid-template-columns: 1fr 1fr;
        grid-template-rows: 1fr 1fr 1fr 0.1fr;
    }
    .top-left-0 {
        grid-area: top-left-0;
    }
    .top-right-0 {
        grid-area: top-right-0;
    }
    .mid-0 {
        grid-area: mid-0;
    }
    .bot-0 {
        grid-area: bot-0;
    }
    /* Theme 1 */
    .container-1 {
        display: grid;
        width: 100%;
        height: 100%;
        grid-template-areas: "top-1 top-1"
        "mid-left-1 mid-right-1"
        "bot-1 bot-1";
        grid-template-columns: 1fr 1fr;
        grid-template-rows: 1fr 1fr 0.1fr;
    }
    .top-1 {
        grid-area: top-1;
    }
    .mid-left-1 {
        grid-area: mid-left-1;
    }
    .mid-right-1 {
        grid-area: mid-right-1;
    }
    .bot-1 {
        grid-area: bot-1;
    }
</style>

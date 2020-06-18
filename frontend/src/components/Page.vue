<template>
    <div id="main">
        <div v-if="page_available">
            <div class="card">
                <div class="container">
                    <div class="far-left-top">
                        <img class="card" :src="image">
                    </div>
                    <div class="left-top">
                        <div style="padding: 16px; text-align: left">
                            <div class="input-group mb-3">
                                <div class="input-group-prepend">
                                    <span class="input-group-text">Name</span>
                                </div>
                                <input type="text" class="form-control" aria-label="Default"
                                       aria-describedby="inputGroup-sizing-default" v-model="name" readonly>
                            </div>
                            <div class="input-group mb-3">
                                <div class="input-group-prepend">
                                    <span class="input-group-text">Phone</span>
                                </div>
                                <input type="text" class="form-control" aria-label="Default"
                                       aria-describedby="inputGroup-sizing-default" v-model="phone" readonly>
                            </div>
                            <div class="input-group mb-3">
                                <div class="input-group-prepend">
                                    <span class="input-group-text">Mobile</span>
                                </div>
                                <input type="text" class="form-control" aria-label="Default"
                                       aria-describedby="inputGroup-sizing-default" v-model="mobile" readonly>
                            </div>
                            <div class="input-group mb-3">
                                <div class="input-group-prepend">
                                    <span class="input-group-text">Height</span>
                                </div>
                                <input type="text" class="form-control" aria-label="Default"
                                       aria-describedby="inputGroup-sizing-default" v-model="height" readonly>
                            </div>
                        </div>
                    </div>
                    <div class="right-top">
                        <div style="padding: 16px; text-align: left">
                            <div class="input-group mb-3">
                                <div class="input-group-prepend">
                                    <span class="input-group-text">Hair color</span>
                                </div>
                                <input type="text" class="form-control" aria-label="Default"
                                       aria-describedby="inputGroup-sizing-default" v-model="haircolor" readonly>
                            </div>
                            <div class="input-group mb-3">
                                <div class="input-group-prepend">
                                    <span class="input-group-text">Eye color</span>
                                </div>
                                <input type="text" class="form-control" aria-label="Default"
                                       aria-describedby="inputGroup-sizing-default" v-model="eyecolor" readonly>
                            </div>
                            <div class="input-group mb-3">
                                <div class="input-group-prepend">
                                    <span class="input-group-text">Birthday</span>
                                </div>
                                <input type="text" class="form-control" aria-label="Default"
                                       aria-describedby="inputGroup-sizing-default" v-model="birthday" readonly>
                            </div>
                            <div class="input-group mb-3">
                                <div class="input-group-prepend">
                                    <span class="input-group-text">Star Sign</span>
                                </div>
                                <input type="text" class="form-control" aria-label="Default"
                                       aria-describedby="inputGroup-sizing-default" v-model="star_sign" readonly>
                            </div>
                        </div>
                    </div>
                    <div class="far-right-top">
                        <div style="padding: 16px; text-align: left">
                            <div class="input-group mb-3">
                                <div class="input-group-prepend">
                                    <span class="input-group-text">Address</span>
                                </div>
                                <input type="text" class="form-control" aria-label="Default"
                                       aria-describedby="inputGroup-sizing-default" v-model="address" readonly>
                            </div>
                            <div class="input-group mb-3">
                                <div class="input-group-prepend">
                                    <span class="input-group-text">Class</span>
                                </div>
                                <input type="text" class="form-control" aria-label="Default"
                                       aria-describedby="inputGroup-sizing-default" v-model="my_class" readonly>
                            </div>
                            <div class="input-group mb-3">
                                <div class="input-group-prepend">
                                    <span class="input-group-text">School</span>
                                </div>
                                <input type="text" class="form-control" aria-label="Default"
                                       aria-describedby="inputGroup-sizing-default" v-model="school" readonly>
                            </div>
                            <div class="input-group mb-3">
                                <div class="input-group-prepend">
                                    <span class="input-group-text">Favorite Subject</span>
                                </div>
                                <input type="text" class="form-control" aria-label="Default"
                                       aria-describedby="inputGroup-sizing-default" v-model="subject" readonly>
                            </div>
                        </div>
                    </div>
                    <div class="far-far-right-top">
                        <div class="far-left-top">
                            <img class="card" :src="sticker1">
                        </div>
                    </div>
                    <div class="left-mid">
                        <div class="far-left-top">
                            <img class="card" id="img-large" :src="sticker2">
                        </div>
                    </div>
                    <div class="right-mid">
                        <div style="padding: 16px; text-align: left">
                            <div class="input-group mb-3">
                                <div class="input-group-prepend">
                                    <span class="input-group-text">Pet</span>
                                </div>
                                <input type="text" class="form-control" aria-label="Default"
                                       aria-describedby="inputGroup-sizing-default" v-model="pet" readonly>
                            </div>
                            <div class="input-group mb-3">
                                <div class="input-group-prepend">
                                    <span class="input-group-text">I love ...</span>
                                </div>
                                <input type="text" class="form-control" aria-label="Default"
                                       aria-describedby="inputGroup-sizing-default" v-model="love" readonly>
                            </div>
                            <div class="input-group mb-3">
                                <div class="input-group-prepend">
                                    <span class="input-group-text">I hate ...</span>
                                </div>
                                <input type="text" class="form-control" aria-label="Default"
                                       aria-describedby="inputGroup-sizing-default" v-model="hate" readonly>
                            </div>
                            <div class="input-group mb-3">
                                <div class="input-group-prepend">
                                    <span class="input-group-text">Dream job</span>
                                </div>
                                <input type="text" class="form-control" aria-label="Default"
                                       aria-describedby="inputGroup-sizing-default" v-model="job" readonly>
                            </div>
                            <div class="input-group mb-3">
                                <div class="input-group-prepend">
                                    <span class="input-group-text">Hobbies</span>
                                </div>
                                <input type="text" class="form-control" aria-label="Default"
                                       aria-describedby="inputGroup-sizing-default" v-model="hobbies" readonly>
                            </div>
                        </div>
                    </div>
                    <div class="left-bot">
                        <div class="far-left-top">
                            <img class="card" :src="sticker3">
                        </div>
                    </div>
                    <div class="mid-bot">
                        <div style="padding: 16px; text-align: left">
                            <div class="input-group mb-3">
                                <div class="input-group-prepend">
                                    <span class="input-group-text">Idol</span>
                                </div>
                                <input type="text" class="form-control" aria-label="Default"
                                       aria-describedby="inputGroup-sizing-default" v-model="idol" readonly>
                            </div>
                            <div class="input-group mb-3">
                                <div class="input-group-prepend">
                                    <span class="input-group-text">Movie</span>
                                </div>
                                <input type="text" class="form-control" aria-label="Default"
                                       aria-describedby="inputGroup-sizing-default" v-model="movie" readonly>
                            </div>
                            <div class="input-group mb-3">
                                <div class="input-group-prepend">
                                    <span class="input-group-text">Sport</span>
                                </div>
                                <input type="text" class="form-control" aria-label="Default"
                                       aria-describedby="inputGroup-sizing-default" v-model="sport" readonly>
                            </div>
                            <div class="input-group mb-3">
                                <div class="input-group-prepend">
                                    <span class="input-group-text">Book</span>
                                </div>
                                <input type="text" class="form-control" aria-label="Default"
                                       aria-describedby="inputGroup-sizing-default" v-model="book" readonly>
                            </div>
                            <div class="input-group mb-3">
                                <div class="input-group-prepend">
                                    <span class="input-group-text">Food</span>
                                </div>
                                <input type="text" class="form-control" aria-label="Default"
                                       aria-describedby="inputGroup-sizing-default" v-model="food" readonly>
                            </div>
                            <div class="input-group mb-3">
                                <div class="input-group-prepend">
                                    <span class="input-group-text">What I wanted to say</span>
                                </div>
                                <input type="text" class="form-control" aria-label="Default"
                                       aria-describedby="inputGroup-sizing-default" v-model="leftover" readonly>
                            </div>
                        </div>
                    </div>
                    <div class="right-bot">
                        <div class="far-left-top">
                            <img class="card" :src="sticker4">
                        </div>
                    </div>
                </div>
                <div class="separator"></div>
                <div>
                    <div style="text-align: left" class="card-body left">
                        <a @click.native="scrollToTop()" v-on:click="back" class="btn btn-primary">Back</a>
                    </div>
                    <div style="text-align: right" class="card-body right">
                        <a @click.native="scrollToTop()" v-on:click="forward" class="btn btn-primary">Next</a>
                    </div>
                </div>
            </div>
        </div>
        <div v-else>
            <h2>Sorry, that's it :/</h2>
            <a @click.native="scrollToTop()" v-on:click="backToBook" class="btn btn-primary">Back to book</a>
        </div>
    </div>
</template>

<script>

    export default {

        name: "Page",
        beforeMount() {
            this.getPage();
        },
        data() {
            return {
                page_available: true,
                uuid: null,
                image: "",
                sticker1: "",
                sticker2: "",
                sticker3: "",
                sticker4: "",
                name: "",
                phone: "",
                mobile: "",
                height: "",
                haircolor: "",
                eyecolor: "",
                birthday: "",
                star_sign: "",
                address: "",
                my_class: "",
                school: "",
                subject: "",
                pet: "",
                love: "",
                hate: "",
                job: "",
                hobbies: "",
                idol: "",
                movie: "",
                sport: "",
                book: "",
                food: "",
                leftover: ""
            }
        },
        methods: {
            scrollToTop() {
                window.scrollTo(0,0);
            },
            getPage() {
                this.$store.dispatch("getPages")
                    .then((response) => {
                        var page = response.data[this.$route.query.num];
                        var desiredPage = this.$route.query.num;
                        var totalPages = response.data.length;
                        if(desiredPage >= totalPages) {
                            this.page_available = false;
                        }
                        console.log(page.mobile)
                        this.uuid = page.uuid;
                        this.name = page.name === "null" ? "" : page.name;
                        this.address = page.address === "null" ? "" : page.address;
                        this.phone = page.telephone === "null" ? "" : page.telephone;
                        this.mobile = page.mobile === "null" ? "" : page.mobile;
                        this.height = page.size === "null" ? "" : page.size;
                        this.haircolor = page.hairColor === "null" ? "" : page.hairColor;
                        this.eyecolor = page.eyeColor === "null" ? "" : page.eyeColor;
                        this.birthday = page.birthday === "null" ? "" : page.birthday;
                        this.pet = page.favoritePet === "null" ? "" : page.favoritePet;
                        this.my_class = page.schoolClass === "null" ? "" : page.schoolClass;
                        this.school = page.school === "null" ? "" : page.school;
                        this.subject = page.favoriteSubject === "null" ? "" : page.favoriteSubject;
                        this.love = page.howToPleaseMe === "null" ? "" : page.howToPleaseMe;
                        this.hate = page.whatIDontLike === "null" ? "" : page.whatIDontLike;
                        this.job = page.favoriteJob === "null" ? "" : page.favoriteJob;
                        this.hobbies = page.myHobbies === "null" ? "" : page.myHobbies;
                        this.idol = page.fanOf === "null" ? "" : page.fanOf;
                        this.movie = page.favoriteMovie === "null" ? "" : page.favoriteMovie;
                        this.sport = page.favoriteSport === "null" ? "" : page.favoriteSport;
                        this.book = page.favoriteBook === "null" ? "" : page.favoriteBook;
                        this.food = page.favoriteFood === "null" ? "" : page.favoriteFood;
                        this.leftover = page.niceComment === "null" ? "" : page.niceComment;
                        this.star_sign = page.starSign === "null" ? "" : page.starSign;
                        this.getImage();
                        this.getImage1();
                        this.getImage2();
                        this.getImage3();
                        this.getImage4();
                    })
            },
            getImage() {
                this.$store.dispatch("getPageImage", {uuid: this.uuid})
                    .then((response) => {
                        this.image = 'data:image/jpeg;base64,'.concat(response.data);
                    })
            },
            getImage1() {
                this.$store.dispatch("getPageSticker", {uuid: this.uuid, number: "0"})
                    .then((response) => {
                        this.sticker1 = 'data:image/jpeg;base64,'.concat(response.data);
                    })
            },
            getImage2() {
                this.$store.dispatch("getPageSticker", {uuid: this.uuid, number: "1"})
                    .then((response) => {
                        this.sticker2 = 'data:image/jpeg;base64,'.concat(response.data);
                    })
            },
            getImage3() {
                this.$store.dispatch("getPageSticker", {uuid: this.uuid, number: "2"})
                    .then((response) => {
                        this.sticker3 = 'data:image/jpeg;base64,'.concat(response.data);
                    })
            },
            getImage4() {
                this.$store.dispatch("getPageSticker", {uuid: this.uuid, number: "3"})
                    .then((response) => {
                        this.sticker4 = 'data:image/jpeg;base64,'.concat(response.data);
                    })
            },
            back : function () {
                var nextPage = +(this.$route.query.num) - 1;
                this.$router.push('/page?num=' + nextPage);
                this.getPage();
                this.scrollToTop();
            },
            forward : function () {
                var nextPage = +(this.$route.query.num) + 1;
                this.$router.push('/page?num=' + nextPage);
                this.getPage();
                this.scrollToTop();
            },
            backToBook : function () {
                this.$router.push('/book');
                this.scrollToTop();
            }
        }
    }
</script>

<style scoped>
    #main {
        padding: 32px;
    }
    .separator {
        width: 100%;
        height: 1px;
        background: black;
    }
    .left {
        width: 50%;
        float: left;
    }
    .right {
        width: 50%;
        float: right;
    }
    img {
        width: 20vh;
    }
    #img-large {
        width: 50vh;
    }
    .container {
        align-self: center;
        display: grid;
        min-width: 100%;
        min-height: 100%;
        margin: 0;
        grid-template-areas: "far-left-top left-top right-top far-right-top far-far-right-top"
        "left-mid left-mid right-mid right-mid right-mid"
        "left-bot mid-bot mid-bot mid-bot right-bot";
        grid-template-columns: 1fr 4fr 4fr 4fr 1fr;
        grid-template-rows: 1fr 1fr 1fr;
    }
    .container > div {
        align-self: center;
        padding: 16px;
    }
    .far-left-top {
        grid-area: far-left-top;
    }
    .left-top {
        grid-area: left-top;
    }
    .right-top {
        grid-area: right-top;
    }
    .far-right-top {
        grid-area: far-right-top;
    }
    .far-far-right-top {
        grid-area: far-far-right-top;
    }
    .left-mid {
        grid-area: left-mid;
    }
    .right-mid {
        grid-area: right-mid;
    }
    .left-bot {
        grid-area: left-bot;
    }
    .mid-bot {
        grid-area: mid-bot;
    }
    .right-bot {
        grid-area: right-bot;
    }
</style>

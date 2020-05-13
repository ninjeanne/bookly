<template>
    <div id="main">
        <div v-if="page_available">
            <div class="card">
                <div class="container">
                    <div class="far-left-top">
                        <img :src="image">
                    </div>
                    <div class="left-top">
                        <div style="padding: 16px; text-align: left">
                            <div class="input-group mb-3">
                                <div class="input-group-prepend">
                                    <span class="input-group-text">Name</span>
                                </div>
                                <input type="text" class="form-control" aria-label="Default"
                                       aria-describedby="inputGroup-sizing-default" v-model="name">
                            </div>
                            <div class="input-group mb-3">
                                <div class="input-group-prepend">
                                    <span class="input-group-text">Phone</span>
                                </div>
                                <input type="text" class="form-control" aria-label="Default"
                                       aria-describedby="inputGroup-sizing-default" v-model="phone">
                            </div>
                            <div class="input-group mb-3">
                                <div class="input-group-prepend">
                                    <span class="input-group-text">Mobile</span>
                                </div>
                                <input type="text" class="form-control" aria-label="Default"
                                       aria-describedby="inputGroup-sizing-default" v-model="mobile">
                            </div>
                            <div class="input-group mb-3">
                                <div class="input-group-prepend">
                                    <span class="input-group-text">Height</span>
                                </div>
                                <input type="text" class="form-control" aria-label="Default"
                                       aria-describedby="inputGroup-sizing-default" v-model="height">
                            </div>
                        </div>
                    </div>
                    <div class="right-top">
                        <div style="padding: 16px; text-align: left">
                            <div class="input-group mb-3">
                                <div class="input-group-prepend">
                                    <span class="input-group-text">Haircolor</span>
                                </div>
                                <input type="text" class="form-control" aria-label="Default"
                                       aria-describedby="inputGroup-sizing-default" v-model="haircolor">
                            </div>
                            <div class="input-group mb-3">
                                <div class="input-group-prepend">
                                    <span class="input-group-text">Eyecolor</span>
                                </div>
                                <input type="text" class="form-control" aria-label="Default"
                                       aria-describedby="inputGroup-sizing-default" v-model="eyecolor">
                            </div>
                            <div class="input-group mb-3">
                                <div class="input-group-prepend">
                                    <span class="input-group-text">Birthday</span>
                                </div>
                                <input type="text" class="form-control" aria-label="Default"
                                       aria-describedby="inputGroup-sizing-default" v-model="birthday">
                            </div>
                            <div class="input-group mb-3">
                                <div class="input-group-prepend">
                                    <span class="input-group-text">Star Sign</span>
                                </div>
                                <input type="text" class="form-control" aria-label="Default"
                                       aria-describedby="inputGroup-sizing-default" v-model="star_sign">
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
                                       aria-describedby="inputGroup-sizing-default" v-model="address">
                            </div>
                            <div class="input-group mb-3">
                                <div class="input-group-prepend">
                                    <span class="input-group-text">Class</span>
                                </div>
                                <input type="text" class="form-control" aria-label="Default"
                                       aria-describedby="inputGroup-sizing-default" v-model="my_class">
                            </div>
                            <div class="input-group mb-3">
                                <div class="input-group-prepend">
                                    <span class="input-group-text">School</span>
                                </div>
                                <input type="text" class="form-control" aria-label="Default"
                                       aria-describedby="inputGroup-sizing-default" v-model="school">
                            </div>
                            <div class="input-group mb-3">
                                <div class="input-group-prepend">
                                    <span class="input-group-text">Favorite Subject</span>
                                </div>
                                <input type="text" class="form-control" aria-label="Default"
                                       aria-describedby="inputGroup-sizing-default" v-model="subject">
                            </div>
                        </div>
                    </div>
                    <div class="far-far-right-top">
                        <div class="far-left-top">
                            <img :src="image">
                        </div>
                    </div>
                    <div class="left-mid">
                        <div class="far-left-top">
                            <img id="img-large" :src="image">
                        </div>
                    </div>
                    <div class="right-mid">
                        <div style="padding: 16px; text-align: left">
                            <div class="input-group mb-3">
                                <div class="input-group-prepend">
                                    <span class="input-group-text">Pet</span>
                                </div>
                                <input type="text" class="form-control" aria-label="Default"
                                       aria-describedby="inputGroup-sizing-default" v-model="pet">
                            </div>
                            <div class="input-group mb-3">
                                <div class="input-group-prepend">
                                    <span class="input-group-text">I love ...</span>
                                </div>
                                <input type="text" class="form-control" aria-label="Default"
                                       aria-describedby="inputGroup-sizing-default" v-model="love">
                            </div>
                            <div class="input-group mb-3">
                                <div class="input-group-prepend">
                                    <span class="input-group-text">I hate ...</span>
                                </div>
                                <input type="text" class="form-control" aria-label="Default"
                                       aria-describedby="inputGroup-sizing-default" v-model="hate">
                            </div>
                            <div class="input-group mb-3">
                                <div class="input-group-prepend">
                                    <span class="input-group-text">Dreamjob</span>
                                </div>
                                <input type="text" class="form-control" aria-label="Default"
                                       aria-describedby="inputGroup-sizing-default" v-model="job">
                            </div>
                            <div class="input-group mb-3">
                                <div class="input-group-prepend">
                                    <span class="input-group-text">Hobbies</span>
                                </div>
                                <input type="text" class="form-control" aria-label="Default"
                                       aria-describedby="inputGroup-sizing-default" v-model="hobbies">
                            </div>
                        </div>
                    </div>
                    <div class="left-bot">
                        <div class="far-left-top">
                            <img :src="image">
                        </div>
                    </div>
                    <div class="mid-bot">
                        <div style="padding: 16px; text-align: left">
                            <div class="input-group mb-3">
                                <div class="input-group-prepend">
                                    <span class="input-group-text">Idol</span>
                                </div>
                                <input type="text" class="form-control" aria-label="Default"
                                       aria-describedby="inputGroup-sizing-default" v-model="idol">
                            </div>
                            <div class="input-group mb-3">
                                <div class="input-group-prepend">
                                    <span class="input-group-text">Movie</span>
                                </div>
                                <input type="text" class="form-control" aria-label="Default"
                                       aria-describedby="inputGroup-sizing-default" v-model="movie">
                            </div>
                            <div class="input-group mb-3">
                                <div class="input-group-prepend">
                                    <span class="input-group-text">Sport</span>
                                </div>
                                <input type="text" class="form-control" aria-label="Default"
                                       aria-describedby="inputGroup-sizing-default" v-model="sport">
                            </div>
                            <div class="input-group mb-3">
                                <div class="input-group-prepend">
                                    <span class="input-group-text">Book</span>
                                </div>
                                <input type="text" class="form-control" aria-label="Default"
                                       aria-describedby="inputGroup-sizing-default" v-model="book">
                            </div>
                            <div class="input-group mb-3">
                                <div class="input-group-prepend">
                                    <span class="input-group-text">Food</span>
                                </div>
                                <input type="text" class="form-control" aria-label="Default"
                                       aria-describedby="inputGroup-sizing-default" v-model="food">
                            </div>
                            <div class="input-group mb-3">
                                <div class="input-group-prepend">
                                    <span class="input-group-text">What I wanted to say</span>
                                </div>
                                <input type="text" class="form-control" aria-label="Default"
                                       aria-describedby="inputGroup-sizing-default" v-model="leftover">
                            </div>
                        </div>
                    </div>
                    <div class="right-bot">
                        <div class="far-left-top">
                            <img :src="image">
                        </div>
                    </div>
                </div>
                <div class="separator"></div>
                <div style="text-align: right" class="card-body">
                    <a v-on:click="back" class="btn btn-primary">Back</a>
                </div>
                <div style="text-align: right" class="card-body">
                    <a v-on:click="forward" class="btn btn-primary">Next</a>
                </div>
            </div>
        </div>
        <div v-else>
            <h2>Sorry, that's it :/</h2>
            <a v-on:click="backToBook" class="btn btn-primary">Back to book</a>
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
            getPage() {
                this.$store.dispatch("getPages")
                    .then((response) => {
                        var page = response.data[this.$route.query.num];
                        var desiredPage = this.$route.query.num;
                        var totalPages = response.data.length;
                        if(desiredPage >= totalPages) {
                            this.page_available = false;
                        }
                        this.uuid = page.uuid;
                        this.name = page.name;
                        this.address = page.address;
                        this.phone = page.telephone;
                        this.mobile = page.mobile;
                        this.height = page.size;
                        this.haircolor = page.hairColor;
                        this.eyecolor = page.eyeColor;
                        this.birthday = page.birthday;
                        this.pet = page.favoritePet;
                        this.my_class = page.schoolClass;
                        this.school = page.school;
                        this.subject = page.favoriteSubject;
                        this.love = page.howToPleaseMe;
                        this.hate = page.whatIDontLike;
                        this.job = page.favoriteJob;
                        this.hobbies = page.myHobbies;
                        this.idol = page.fanOf;
                        this.movie = page.favoriteMovie;
                        this.sport = page.favoriteSport;
                        this.book = page.favoriteBook;
                        this.food = page.favoriteFood;
                        this.leftover = page.niceComment;
                        this.star_sign = page.starSign;
                        this.getImage();
                    })
            },
            getImage() {
                this.$store.dispatch("getPageImage", {uuid: this.uuid})
                    .then((response) => {
                        this.image = 'data:image/jpeg;base64,'.concat(response.data);
                    })
            },
            back : function () {
                var nextPage = +(this.$route.query.num) - 1;
                this.$router.push('/page?num=' + nextPage);
                this.getPage();
            },
            forward : function () {
                var nextPage = +(this.$route.query.num) + 1;
                this.$router.push('/page?num=' + nextPage);
                this.getPage();
            },
            backToBook : function () {
                this.$router.push('/book');
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
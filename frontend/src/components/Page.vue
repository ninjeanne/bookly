<template>
    <div id="main">
        <div v-if="page_available">
            <div class="card">
                <div class="content">
                    <div class="wrapper">
                        <div class="left">
                            <div style="padding: 16px; text-align: left">
                                <p class="font-weight-normal">Name: {{ user_name }}</p>
                                <p class="font-weight-normal">Address: {{ user_address }}</p>
                                <p class="font-weight-normal">Phone: {{ user_phone }}</p>
                                <p class="font-weight-normal">Height: {{ user_height }}</p>
                                <p class="font-weight-normal">Hair Color: {{ user_haircolor }}</p>
                                <p class="font-weight-normal">Eye Color: {{ user_eyecolor }}</p>
                                <p class="font-weight-normal">Birthday: {{ user_birthday }}</p>
                                <p class="font-weight-normal">Pet: {{ user_pet }}</p>
                            </div>
                            <div style="padding: 16px; text-align: left">
                                <p class="font-weight-normal">What I love: {{ user_love }}</p>
                                <p class="font-weight-normal">What I don't like: {{ user_hate }}</p>
                                <p class="font-weight-normal">Dream Job: {{ user_job }}</p>
                                <p class="font-weight-normal">Hobbies: {{ user_hobbies }}</p>
                                <p class="font-weight-normal">My idol: {{ user_idol }}</p>
                            </div>
                            <div style="padding: 16px; text-align: left">
                                <p class="font-weight-normal">What I want to tell you: {{ user_leftover }}</p>
                            </div>
                        </div>
                        <div class="right">
                            <div style="padding: 16px; text-align: left">
                                <p class="font-weight-normal">Class: {{ user_class }}</p>
                                <p class="font-weight-normal">School: {{ user_school }}</p>
                                <p class="font-weight-normal">Favorite Subject: {{ user_subject }}</p>
                            </div>
                            <div style="padding: 16px; text-align: left">
                                <p class="font-weight-normal">Favorite Movie/Series: {{ user_movie }}</p>
                                <p class="font-weight-normal">Favorite Sport: {{ user_sport }}</p>
                                <p class="font-weight-normal">Favorite Book: {{ user_book }}</p>
                                <p class="font-weight-normal">Favorite Food: {{ user_food }}</p>
                            </div>
                            <div class="sticker">
                                <img src="./../assets/stitch.png">
                            </div>
                        </div>
                    </div>
                    <div style="clear: both"></div>
                </div>
                <div class="separator"></div>
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
                user_name: "",
                user_address: "",
                user_phone: "",
                user_height: "",
                user_haircolor: "",
                user_eyecolor: "",
                user_birthday: "",
                user_pet: "",
                user_class: "",
                user_school: "",
                user_subject: "",
                user_love: "",
                user_hate: "",
                user_job: "",
                user_hobbies: "",
                user_idol: "",
                user_movie: "",
                user_sport: "",
                user_book: "",
                user_food: "",
                user_leftover: "",
            }
        },
        methods: {
            sample() {
                this.user_name= "Sarude Dandstorm";
                this.user_address = "Starlight 11";
                this.user_phone = "0173219273";
                this.user_height ="1.53";
                this.user_haircolor = "Blond";
                this.user_eyecolor = "Green";
                this.user_birthday = "10.02.2001";
                this.user_pet = "Giraffe";
                this.user_class = "10B2";
                this.user_school = "Elon-Musk-Highschool";
                this.user_subject = "English";
                this.user_love = "Dooomi <3";
                this.user_hate = "Php";
                this.user_job = "Astronaut";
                this.user_hobbies = "Daydreaming";
                this.user_idol = "Iron Man";
                this.user_movie = "Avengers: Endgame";
                this.user_sport = "Sleeping";
                this.user_book = "Harry Potter";
                this.user_food = "Spaghetti";
                this.user_leftover = "Cheer up, the worst is yet to come";
            },
            getPage() {
                this.$store.dispatch("getPage")
                    .then((response) => {
                        var page = response.data[this.$route.query.num];
                        var desiredPage = this.$route.query.num;
                        var totalPages = response.data.length;
                        if(desiredPage >= totalPages) {
                            this.page_available = false;
                        }
                        this.user_name = page.name.description;
                        this.user_address = page.address.description;
                        this.user_phone = page.telephone.description;
                        this.user_height = page.size.description;
                        this.user_haircolor = page.hair_color.description;
                        this.user_eyecolor = page.eye_color.description;
                        this.user_birthday = page.birthday.description;
                        this.user_pet = page.favorite_pet.description;
                        this.user_class = page.school_class.description;
                        this.user_school = page.school.description;
                        this.user_subject = page.favorite_subject.description;
                        this.user_love = page.how_to_please_me.description;
                        this.user_hate = page.what_i_dont_like.description;
                        this.user_job = page.favorite_job.description;
                        this.user_hobbies = page.my_hobbies.description;
                        this.user_idol = page.fan_of.description;
                        this.user_movie = page.favorite_movie.description;
                        this.user_sport = page.favorite_sport.description;
                        this.user_book = page.favorite_book.description;
                        this.user_food = page.favorite_food.description;
                        this.user_leftover = page.leftover.description;
                    })
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
    .left {
        width: 50%;
        float: left;
    }
    .right {
        width: 50%;
        float: right;
    }
    img {
        max-width: 30%;
        margin: 16px;
    }
</style>
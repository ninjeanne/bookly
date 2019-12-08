<template>
    <div id="main">
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
                <a href="#" class="btn btn-primary">Next</a>
            </div>
        </div>
    </div>
</template>

<script>
    import store from './../store';
    export default {

        name: "Page",
        beforeMount() {
            this.username = store.getters.getUserName;
            if(this.username == null) {
                this.getUser();
            } else {
                this.isLoggedIn = true;
            }
            this.sample();
        },
        data() {
            return {
                username: "",
                isLoggedIn: false,
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
            getUser() {
                this.$store.dispatch("user")
                    .then(() => {
                        this.username = this.$store.getters.getUserName;
                        this.isLoggedIn = true;
                    })
            },
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
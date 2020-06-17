<template>
    <div v-if="uuid_exists" class="main">
        <div class="container">
            <div class="far-left-top">
                <picture-input
                        ref="pictureInput"
                        @change="onChanged"
                        @remove="onRemoved"
                        :width="500"
                        :removable="true"
                        :height="500"
                        accept="image/jpeg, image/png"
                        :customStrings="{drag: 'Drag and drop your image here'}">
                </picture-input>
                <button class="btn btn-primary" @click="attemptUploadImage">Upload image</button>
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
                <picture-input
                        ref="pictureInputSticker1"
                        @change="onChangedSticker1"
                        @remove="onRemovedSticker1"
                        :width="500"
                        :removable="true"
                        :height="500"
                        accept="image/jpeg, image/png"
                        :customStrings="{drag: 'Drag and drop your image here'}">
                </picture-input>
                <button class="btn btn-primary" @click="attemptUploadSticker1">Upload first sticker</button>
            </div>
            <div class="left-mid">
                <picture-input
                        ref="pictureInputSticker2"
                        @change="onChangedSticker2"
                        @remove="onRemovedSticker2"
                        :width="500"
                        :removable="true"
                        :height="500"
                        accept="image/jpeg, image/png"
                        :customStrings="{drag: 'Drag and drop your image here'}">
                </picture-input>
                <button class="btn btn-primary" @click="attemptUploadSticker2">Upload second sticker</button>
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
                <picture-input
                        ref="pictureInputSticker3"
                        @change="onChangedSticker3"
                        @remove="onRemovedSticker3"
                        :width="500"
                        :removable="true"
                        :height="500"
                        accept="image/jpeg, image/png"
                        :customStrings="{drag: 'Drag and drop your image here'}">
                </picture-input>
                <button class="btn btn-primary" @click="attemptUploadSticker3">Upload third sticker</button>
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
                <picture-input
                        ref="pictureInputSticker4"
                        @change="onChangedSticker4"
                        @remove="onRemovedSticker4"
                        :width="500"
                        :removable="true"
                        :height="500"
                        accept="image/jpeg, image/png"
                        :customStrings="{drag: 'Drag and drop your image here'}">
                </picture-input>
                <button class="btn btn-primary" @click="attemptUploadSticker4">Upload fourth sticker</button>
            </div>
        </div>
        <button v-on:click="save" style="width: 90%; margin-top: 16px" class="btn btn-primary">Save</button>
    </div>
    <div v-else class="main">
        <h2>Whoops, something went wrong :/</h2>
        <h3>Please check your code</h3>
    </div>
</template>

<script>
    import PictureInput from 'vue-picture-input'

    export default {
        name: "PageEditor",

        components: {
            PictureInput
        },
        beforeMount() {
            this.getPage();
        },
        data() {
            return {
                uuid: "",
                uuid_exists: false,
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
            getPage() {
                this.uuid = this.$route.query.id;
                this.$store.dispatch("getPage", {uuid: this.uuid})
                    .then((response) => {
                        this.uuid_exists = true;
                        this.parsePage(response);
                    })
                this.$store.dispatch("getPageImage", {uuid: this.uuid})
                    .then((response) => { })
            },
            save() {
                var text = '{ "uuid":"' + this.uuid + '",' +
                    ' "address":"' + this.address + '",' +
                    ' "birthday":"' + this.birthday + '",' +
                    ' "eyeColor":"' + this.eyecolor + '",' +
                    ' "fanOf":"' + this.idol + '",' +
                    ' "favoriteBook":"' + this.book + '",' +
                    ' "favoriteFood":"' + this.food + '",' +
                    ' "favoriteJob":"' + this.job + '",' +
                    ' "favoriteMovie":"' + this.movie + '",' +
                    ' "favoritePet":"' + this.pet + '",' +
                    ' "favoriteSport":"' + this.sport + '",' +
                    ' "favoriteSubject":"' + this.subject + '",' +
                    ' "hairColor":"' + this.haircolor + '",' +
                    ' "howToPleaseMe":"' + this.love + '",' +
                    ' "whatIDontLike":"' + this.hate + '",' +
                    ' "leftOver":"' + this.leftover + '",' +
                    ' "mobile":"' + this.mobile + '",' +
                    ' "myHobbies":"' + this.hobbies + '",' +
                    ' "name":"' + this.name + '",' +
                    ' "niceComment":"' + this.leftover + '",' +
                    ' "school":"' + this.school + '",' +
                    ' "schoolClass":"' + this.my_class + '",' +
                    ' "size":"' + this.height + '",' +
                    ' "telephone":"' + this.phone + '",' +
                    ' "starSign":"' + this.star_sign + '"' +
                    ' }';
                this.$store.dispatch("updatePage", {json: text})
                    .then((response) => {
                        alert("saved");
                    })
            },
            parsePage(response) {
                this.name = response.data.name === "null" ? "" : response.data.name;
                this.address = response.data.address === "null" ? "" : response.data.address;
                this.phone = response.data.telephone === "null" ? "" : response.data.telephone;
                this.mobile = response.data.mobile === "null" ? "" : response.data.mobile;
                this.height = response.data.size === "null" ? "" : response.data.size;
                this.haircolor = response.data.hairColor === "null" ? "" : response.data.hairColor;
                this.eyecolor = response.data.eyeColor === "null" ? "" : response.data.eyeColor;
                this.birthday = response.data.birthday === "null" ? "" : response.data.birthday;
                this.pet = response.data.favoritePet === "null" ? "" : response.data.favoritePet;
                this.my_class = response.data.schoolClass === "null" ? "" : response.data.schoolClass;
                this.school = response.data.school === "null" ? "" : response.data.school;
                this.subject = response.data.favoriteSubject === "null" ? "" : response.data.favoriteSubject;
                this.love = response.data.howToPleaseMe === "null" ? "" : response.data.howToPleaseMe;
                this.hate = response.data.whatIDontLike === "null" ? "" : response.data.whatIDontLike;
                this.job = response.data.favoriteJob === "null" ? "" : response.data.favoriteJob;
                this.hobbies = response.data.myHobbies === "null" ? "" : response.data.myHobbies;
                this.idol = response.data.fanOf === "null" ? "" : response.data.fanOf;
                this.movie = response.data.favoriteMovie === "null" ? "" : response.data.favoriteMovie;
                this.sport = response.data.favoriteSport === "null" ? "" : response.data.favoriteSport;
                this.book = response.data.favoriteBook === "null" ? "" : response.data.favoriteBook;
                this.food = response.data.favoriteFood === "null" ? "" : response.data.favoriteFood;
                this.leftover = response.data.niceComment === "null" ? "" : response.data.niceComment;
                this.star_sign = response.data.starSign === "null" ? "" : response.data.starSign;
            },
            onChanged() {
                if (this.$refs.pictureInput.file) {
                    this.image = this.$refs.pictureInput.file;
                } else {
                    console.log("Old browser. No support for Filereader API");
                }
            },
            onRemoved() {
                this.image = '';
            },
            attemptUploadImage() {
                if (this.image) {
                    this.$store.dispatch("updatePageImage", {uuid: this.uuid, image: this.image})
                        .then((response) => { })
                }
            },
            onChangedSticker1() {
                if (this.$refs.pictureInputSticker1.file) {
                    this.sticker1 = this.$refs.pictureInputSticker1.file;
                } else {
                    console.log("Old browser. No support for Filereader API");
                }
            },
            onRemovedSticker1() {
                this.sticker1 = '';
            },
            attemptUploadSticker1() {
                if (this.sticker1) {
                    this.$store.dispatch("editPageSticker", {uuid: this.uuid, image: this.sticker1, number: 0})
                        .then((response) => { })
                }
            },
            onChangedSticker2() {
                if (this.$refs.pictureInputSticker2.file) {
                    this.sticker2 = this.$refs.pictureInputSticker2.file;
                } else {
                    console.log("Old browser. No support for Filereader API");
                }
            },
            onRemovedSticker2() {
                this.sticker2 = '';
            },
            attemptUploadSticker2() {
                if (this.sticker2) {
                    this.$store.dispatch("editPageSticker", {uuid: this.uuid, image: this.sticker2, number: 1})
                        .then((response) => { })
                }
            },
            onChangedSticker3() {
                if (this.$refs.pictureInputSticker3.file) {
                    this.sticker3 = this.$refs.pictureInputSticker3.file;
                } else {
                    console.log("Old browser. No support for Filereader API");
                }
            },
            onRemovedSticker3() {
                this.sticker3 = '';
            },
            attemptUploadSticker3() {
                if (this.sticker3) {
                    this.$store.dispatch("editPageSticker", {uuid: this.uuid, image: this.sticker3, number: 2})
                        .then((response) => { })
                }
            },
            onChangedSticker4() {
                if (this.$refs.pictureInputSticker4.file) {
                    this.sticker4 = this.$refs.pictureInputSticker4.file;
                } else {
                    console.log("Old browser. No support for Filereader API");
                }
            },
            onRemovedSticker4() {
                this.sticker4 = '';
            },
            attemptUploadSticker4() {
                if (this.sticker4) {
                    this.$store.dispatch("editPageSticker", {uuid: this.uuid, image: this.sticker4, number: 3})
                        .then((response) => { })
                }
            },
        }
    }
</script>

<style scoped>
    .main {
        padding: 16px;
    }
    img {
        max-width: 25vh;
        max-height: 25vh;
    }
    button {
        margin-top: 16px;
    }
    .container {
        display: grid;
        width: 100%;
        height: 100%;
        grid-template-areas: "far-left-top left-top right-top far-right-top far-far-right-top"
        "left-mid left-mid right-mid right-mid right-mid"
        "left-bot mid-bot mid-bot mid-bot right-bot";
        grid-template-columns: 1fr 1fr 1fr 1fr 1fr;
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

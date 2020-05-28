<template>
    <div id="main">
        <div class="wrapper">
            <div class="left">
                <form id="app" @submit.prevent="editBook">
                    <label for="title">Title of your Book</label>
                    <br>
                    <input id="title" v-model="title" type="text" name="title">
                    <br>
                    <label for="subtitle">Title of your Book</label>
                    <br>
                    <input id="subtitle" v-model="subtitle" type="text" name="subtitle">
                    <br>
                    <button type="submit" class="btn btn-primary">Confirm</button>
                </form>
            </div>
            <div class="right">
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
                <button class="btn btn-primary" @click="attemptUploadImage">Upload</button>
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
                <button class="btn btn-primary" @click="attemptUploadSticker1">Upload</button>
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
                <button class="btn btn-primary" @click="attemptUploadSticker2">Upload</button>
            </div>
        </div>
        <div style="clear: both"></div>
    </div>
</template>

<script>
    import PictureInput from 'vue-picture-input'

    export default {
        name: "BookEditor",
        components: {
            PictureInput
        },
        beforeMount() {
          this.getBook();
        },
        data() {
            return {
                username: '',
                title: "",
                subtitle: "",
                image: "",
                sticker1: "",
                sticker2: ""
            }
        },
        methods: {
            getBook() {
                this.$store.dispatch("getBook")
                    .then((response) => {
                        this.title = response.data.title;
                        this.subtitle = response.data.subtitle;
                    })
            },
            editBook() {
                this.$store.dispatch("editBook", {title: this.title, subtitle: this.subtitle})
                    .then((response) => { })
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
                    this.$store.dispatch("editBookCover", {image: this.image})
                        .then((response) => {
                        })
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
                    this.$store.dispatch("editBookSticker", {image: this.sticker1, number: "1"})
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
                    this.$store.dispatch("editBookSticker", {image: this.sticker2, number: "2"})
                        .then((response) => { })
                }
            }
        }
    }
</script>

<style scoped>
    #main {
        padding-top: 32px;
    }
    label {
        margin-top: 16px;
    }
    .left {
        width: 50%;
        float: left;
        padding: 8px;
        margin: 0 auto;
    }
    .right {
        width: 50%;
        float: right;
        padding: 8px;
    }
    button {
        margin-top: 16px;
    }
</style>
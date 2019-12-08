<template>
    <div id="main" v-if="isLoggedIn">
        <div class="wrapper">
            <div class="left">
                <form id="app" @submit.prevent="editBook">
                    <label for="title">Title of your Book</label>
                    <br>
                    <input id="title" v-model="title" type="text" name="title">
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
                <button class="btn btn-primary" @click="attemptUpload" v-bind:class="{ disabled: !image }">Upload</button>
            </div>
        </div>
        <div style="clear: both"></div>
    </div>
    <div v-else>
        <h2>You must be logged in</h2>
        <a href="/login">Click here to login!</a>
    </div>
</template>

<script>
    import store from './../store';
    import PictureInput from 'vue-picture-input'

    export default {
        name: "BookEditor",
        components: {
            PictureInput
        },
        beforeMount() {
            this.username = store.getters.getUserName;
            if(this.username == null) {
                this.getUser();
            } else {
                this.isLoggedIn = true;
            }
        },
        data() {
            return {
                username: '',
                isLoggedIn: false,
                title: "",
                subtitle: "",
                image: ""
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
            editBook() {
                this.$store.dispatch("editBook", {title: this.title})
                    .then((response) => {
                        if(response.status === 200) {
                            alert("successful");
                        }
                    })
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
            attemptUpload() {
                if (this.image) {
                    this.$store.dispatch("editBookCover", {image: this.image})
                        .then((response) => {
                            if(response.status === 200) {
                                alert("successful");
                            }
                        })
                }
            }
        }
    }
</script>

<style scoped>
    #main {
        padding-top: 32px;
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
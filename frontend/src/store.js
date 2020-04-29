import Vue from 'vue'
import Vuex from 'vuex'
import api from './components/backend-api'

Vue.use(Vuex);

export default new Vuex.Store({
    state: { },
    mutations: { },
    actions: {
        user() {
            return new Promise((resolve) => {
                    api.getUser()
                        .then(response => {
                            resolve(response)
                        })
                        .catch(err => {
                            console.error(err);
                        });
            })
        },
        getBook() {
            return new Promise((resolve) => {
                api.getBook()
                    .then(response => {
                        resolve(response)
                    })
                    .catch(err => {
                        console.error(err);
                    });
            })
        },
        editBook({commit}, {title}) {
            return new Promise((resolve) => {
                api.editBook(title)
                    .then(response => {
                        resolve(response)
                    })
                    .catch(err => {
                        console.error(err);
                    });
            })
        },
        getBookCover() {
            return new Promise((resolve) => {
                api.getBookCover()
                        .then((response) => {
                            if(response.status === 200) {
                                resolve(response)
                            }
                        })
                        .catch(err=>{
                            console.error(err);
                        });
            })
        },
        editBookCover({commit}, {image}) {
            return new Promise((resolve) => {
                api.editBookCover(image)
                    .then(response => {
                        if(response.status === 200) {
                            console.log("success");
                        }
                        resolve(response)
                    })
                    .catch(err=>{
                        console.error(err);
                    });
            })
        },
        getPage() {
            return new Promise((resolve) => {
                api.getPage()
                    .then(response => {
                        resolve(response)
                    })
                    .catch(err => {
                        console.error(err);
                    });
            })
        }
    },
    getters: { }
})
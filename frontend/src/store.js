import Vue from 'vue'
import Vuex from 'vuex'
import api from './components/backend-api'
import apipublic from './components/backend-api-public'

Vue.use(Vuex);

export default new Vuex.Store({
    state: { },
    mutations: { },
    actions: {
        getUser() {
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
        getPages() {
            return new Promise((resolve) => {
                api.getPages()
                    .then(response => {
                        resolve(response)
                    })
                    .catch(err => {
                        console.error(err);
                    });
            })
        },
        getPage({ },{uuid}) {
            return new Promise((resolve) => {
                apipublic.getPage(uuid)
                    .then(response => {
                        resolve(response)
                    })
                    .catch(err => {
                        console.error(err);
                    });
            })
        },
        newPage() {
            return new Promise((resolve) => {
                api.newPage()
                    .then(response => {
                        resolve(response)
                    })
                    .catch(err => {
                        console.error(err);
                    });
            })
        },
        updatePage({ },{json}) {
            return new Promise((resolve) => {
                apipublic.updatePage(json)
                    .then(response => {
                        resolve(response)
                    })
                    .catch(err => {
                        console.error(err);
                    });
            })
        },
        getPageImage({ },{uuid}) {
            return new Promise((resolve) => {
                apipublic.getPageImage(uuid)
                    .then(response => {
                        resolve(response)
                    })
                    .catch(err => {
                        console.error(err);
                    });
            })
        },
        updatePageImage({ },{uuid, image}) {
            return new Promise((resolve) => {
                apipublic.updatePageImage(uuid, image)
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
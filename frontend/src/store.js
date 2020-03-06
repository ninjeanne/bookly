import Vue from 'vue'
import Vuex from 'vuex'
import api from './components/backend-api'

Vue.use(Vuex);

export default new Vuex.Store({
    state: {
        userName: null,
        userPass: null,
        userBook: null,
    },
    mutations: {
        login_success(state, payload) {
            state.userName = payload.userName;
            state.userPass = payload.userPass;
        },
        book_retrieved(state, payload) {
            state.userBook = payload;
        }
    },
    actions: {
        login({commit}, {user, password}) {
            return new Promise((resolve) => {
                api.getSecured(user, password)
                    .then(response => {
                        if(response.status === 200) {
                            commit('login_success', {
                                userName: user,
                                userPass: password
                            });
                            localStorage.setItem("auth", btoa(`${user}:${password}`));
                        }
                        resolve(response)
                    })
                    .catch(err => {
                        console.error(err);
                    });
            })
        },
        register({commit}, {user, email, password1, password2}) {
            return new Promise((resolve, reject) => {
                api.register(user, email, password1, password2)
                    .then(response => {
                        if (response.status === 200) { }
                        resolve(response)
                    })
                    .catch(err => {
                        console.error(err);
                    });
            })
        },
        user({commit}) {
            return new Promise((resolve) => {
                    api.getUser()
                        .then(response => {
                            console.log(response)
                            if (response.status === 200) {
                                commit('login_success', {
                                    userName: response.data.username
                                });
                            }
                            resolve(response)
                        })
                        .catch(err => {
                            console.error(err);
                        });
            })
        },
        getBook({commit}) {
            return new Promise((resolve) => {
                api.getBook(localStorage.getItem("auth"))
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
                api.editBook(localStorage.getItem("auth"), title)
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
                api.getBookCover(localStorage.getItem("auth"))
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
                api.editBookCover(localStorage.getItem("auth"), image)
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
        }
    },
    getters: {
        getUserName: state => state.userName,
        getUserPass: state => state.userPass,
        getBook: state => state.userBook
    }
})

import Vue from 'vue'
import Vuex from 'vuex'
import api from './components/backend-api'

Vue.use(Vuex);

export default new Vuex.Store({
    state: {
        loginSuccess: false,
        loginError: false,
        userName: null,
        userPass: null
    },
    mutations: {
        login_success(state, payload){
            state.loginSuccess = true;
            state.loginError = false;
            state.userName = payload.userName;
            state.userPass = payload.userPass;
        },
        login_error(state){
            state.loginSuccess = false;
            state.loginError = true;
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
                    .catch(() => {
                        commit('login_error');
                    })
            })
        },
        register({commit}, {user, email, password1, password2}) {
            return new Promise((resolve, reject) => {
                api.createUser(user, email, password1, password2)
                    .then(response => {
                        if (response.status === 200) {

                        }
                        resolve(response)
                    })
                    .catch(error => {
                        commit('register_error', {
                            userName: user
                        });
                        reject("Invalid credentials!")
                    })
            })
        },
        user({commit}) {
            return new Promise((resolve) => {
                if(localStorage.getItem("auth")) {
                    api.getUser(localStorage.getItem("auth"))
                        .then(response => {
                            if (response.status === 200) {
                                commit('login_success', {
                                    userName: response.data.username
                                });
                                console.log(response.data.username);
                            }
                            resolve(response)
                        })
                } else {
                    commit('login_error', {});
                }
            })
        },
    },
    getters: {
        isLoggedIn: state => state.loginSuccess,
        hasLoginErrored: state => state.loginError,
        getUserName: state => state.userName,
        getUserPass: state => state.userPass
    }
})
import Vue from 'vue'
import App from './App.vue'
import router from './router'
import BootstrapVue from 'bootstrap-vue'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import Keycloak from 'keycloak-js';
import store from './store'


Vue.config.productionTip = false;

// Bootstrap
Vue.use(BootstrapVue);

const keycloakConfig = {
    realm: 'bookly',
    url: 'https://keycloak.bookly.online/auth',
    clientId: 'bookly-app',
    onLoad: "check-sso",
    "enable-cors": true
};
const keycloak = Keycloak(keycloakConfig);
keycloak.init({
    onLoad: keycloakConfig.onLoad
}).success((auth) => {
    new Vue({
        router,
        store,
        render: h => h(App)
    }).$mount('#app');

    localStorage.setItem("auth", auth.toString());
    localStorage.setItem("vue-token", keycloak.token);
    localStorage.setItem("vue-refresh-token", keycloak.refreshToken);

    // To fetch UserInfo like name, email id etc.,
    keycloak.loadUserInfo().success(userInfo => {
        localStorage.setItem('userInfo', JSON.stringify(userInfo));
    })
    //Every 60 second check if token expires soon (here 70 seconds used in updateToken), if so, refresh it.
    setInterval(() =>{
        // updateToken parameter is the minValidaty time
        keycloak.updateToken(70).success((refreshed)=>{
            if (refreshed) {
                console.log('Token refreshed'+ refreshed);
            } else {
                console.log('Token not refreshed, valid for '
                    + Math.round(keycloak.tokenParsed.exp + keycloak.timeSkew - new Date().getTime() / 1000) + ' seconds');
            }
        }).error(()=>{
            console.log('Failed to refresh token');
        });


    }, 60000)

}).error(() =>{
    console.log("Authenticated Failed");
});

router.beforeEach((to, from, next) => {
    if (to.meta.requiresAuth && localStorage.getItem("auth") === 'false') {
        let host = location.host;
        if(host.includes('localhost')){
            host = 'http://' + host;
        } else {
            host = 'https://' + host;
        }
        keycloak.login({
            prompt: "login",
            redirectUri: host
        })
    } else {
        next()
    }
});

export default keycloak
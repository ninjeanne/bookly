import Vue from 'vue'
import Router from 'vue-router'
import Home from '@/components/Home'
import User from '@/components/User'
import Login from '@/components/Login'
import Register from "@/components/Register"
import Book from '@/components/Book'
import Help from '@/components/Help'
import About from '@/components/About'
import TermsOfService from "@/components/TermsOfService";

import store from './store'

Vue.use(Router);

const router = new Router({
    mode: 'history', // uris without hashes #, see https://router.vuejs.org/guide/essentials/history-mode.html#html5-history-mode
    routes: [
        { path: '/', component: Home },
        { path: '/user', component: User },
        { path: '/login', component: Login },
        { path: '/register', component: Register},
        { path: '/book', component: Book},
        { path: '/help', component: Help },
        { path: '/about', component: About},
        { path: '/termsofservice', component: TermsOfService},
        // otherwise redirect to home
        { path: '*', redirect: '/' }
    ]
});

router.beforeEach((to, from, next) => {
    if (to.matched.some(record => record.meta.requiresAuth)) {
        // this route requires auth, check if logged in
        // if not, redirect to login page.
        if (!store.getters.isLoggedIn) {
            next({
                path: '/login'
            })
        } else {
            next();
        }
    } else {
        next(); // make sure to always call next()!
    }
});

export default router;
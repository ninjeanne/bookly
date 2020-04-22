import Vue from 'vue'
import Router from 'vue-router'
import Home from '@/components/Home'
import User from '@/components/User'
import Book from '@/components/Book'
import BookEditor from '@/components/BookEditor'
import Help from '@/components/Help'
import About from '@/components/About'
import TermsOfService from '@/components/TermsOfService'
import Page from '@/components/Page';

Vue.use(Router);

const router = new Router({
    mode: 'history',
    routes: [
        { path: '/', name: 'home', component: Home, meta: { requiresAuth: false }},
        { path: '/login', name: 'login', component: Page, meta: { requiresAuth: false }, beforeEnter() {location.href = 'https://keycloak.bookly.online/auth'}},
        { path: '/user', component: User, meta: { requiresAuth: true }},
        { path: '/book', component: Book, meta: { requiresAuth: true }},
        { path: '/bookeditor', component: BookEditor, meta: { requiresAuth: true }},
        { path: '/help', component: Help, meta: { requiresAuth: false }},
        { path: '/about', component: About, meta: { requiresAuth: false }},
        { path: '/termsofservice', component: TermsOfService, meta: { requiresAuth: false }},
        { path: '/page', component: Page, meta: { requiresAuth: true }},
        // otherwise redirect to home
        { path: '*', redirect: '/'}
    ]
})

router.beforeEach((to, from, next) => {
    console.log("auth: " + to.meta.requiresAuth)
    if (to.meta.requiresAuth) {
        next({name: 'login'})
    }
    next()
})

export default router;

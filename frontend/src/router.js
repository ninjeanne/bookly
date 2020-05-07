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
import PageEditor from "./components/PageEditor";

Vue.use(Router);

const router = new Router({
    mode: 'history',
    routes: [
        { path: '/', name: 'home', component: Home, meta: { requiresAuth: false }},
        { path: '/login', name: 'login', component: Page, meta: { requiresAuth: false }},
        { path: '/user', component: User, meta: { requiresAuth: true }},
        { path: '/book', component: Book, meta: { requiresAuth: true }},
        { path: '/bookeditor', component: BookEditor, meta: { requiresAuth: true }},
        { path: '/help', component: Help, meta: { requiresAuth: false }},
        { path: '/about', component: About, meta: { requiresAuth: false }},
        { path: '/termsofservice', component: TermsOfService, meta: { requiresAuth: false }},
        { path: '/page', component: Page, meta: { requiresAuth: true }},
        { path: '/pageeditor', component: PageEditor, meta: { requiresAuth: false }},
        // otherwise redirect to home
        { path: '*', redirect: '/'}
    ]
})

export default router;

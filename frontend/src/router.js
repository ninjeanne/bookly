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
        { path: '/', name: 'home', component: Home, meta: { title: "Welcome",requiresAuth: false }},
        { path: '/login', name: 'login', component: Page, meta: { requiresAuth: false }},
        { path: '/user', component: User, meta: { title: "Profile", requiresAuth: true }},
        { path: '/book', component: Book, meta: { title: "Friendship book", requiresAuth: true }},
        { path: '/bookeditor', component: BookEditor, meta: { title: "Book editor", requiresAuth: true }},
        { path: '/help', component: Help, meta: { title: "Help", requiresAuth: false }},
        { path: '/about', component: About, meta: { title: "About us", requiresAuth: false }},
        { path: '/termsofservice', component: TermsOfService, meta: { title: "Terms of service", requiresAuth: false }},
        { path: '/page', component: Page, meta: { title: "Pages", requiresAuth: true }},
        { path: '/pageeditor', component: PageEditor, meta: { title: "Page editor", requiresAuth: false }},
        // otherwise redirect to home
        { path: '*', redirect: '/'}
    ]
})

export default router;

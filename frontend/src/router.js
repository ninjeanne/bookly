import Vue from 'vue'
import Router from 'vue-router'
import Home from '@/components/Home'
import User from '@/components/User'
import Login from '@/components/Login'
import Register from '@/components/Register'
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
        { path: '/', component: Home},
        { path: '/user', component: User},
        { path: '/login', component: Login},
        { path: '/register', component: Register},
        { path: '/book', component: Book},
        { path: '/bookeditor', component: BookEditor},
        { path: '/help', component: Help},
        { path: '/about', component: About},
        { path: '/termsofservice', component: TermsOfService},
        { path: '/page', component: Page},
        // otherwise redirect to home
        { path: '*', redirect: '/' }
    ]
});

router.beforeEach((to, from, next) => {
    const publicPages = ["/login", "/home", "/help", "/about", "/termsofservice", "/"];
    const authRequired = !publicPages.includes(to.path);
    const loggedIn = localStorage.getItem("auth");
    if(authRequired && !loggedIn){
        return next({
            path: "/login",
        })
    } else {
        next();
    }
});

export default router;
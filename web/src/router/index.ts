import {createRouter, createWebHistory, RouteRecordRaw} from 'vue-router'
import EditorView from "@/views/admin/editor.vue";
import FileEditorView from "@/views/admin/editor/file.vue";
import ServerEditorView from "@/views/admin/editor/server.vue";
import DialogEditorView from "@/views/admin/editor/dialog.vue";
import WikiEditorView from "@/views/admin/editor/wiki.vue";

import Home from "@/views/home.vue";
import About from "@/views/about.vue";
import store from "@/store";

const routes: Array<RouteRecordRaw> = [
    {
        path: '/',
        name: 'index',
        redirect: '/home'
    },
    {
        path: '/home',
        name: 'Home',
        component: Home,
    },
    {
        path: '/about',
        name: 'About',
        component: About,
    },
    {
        path: '/editor',
        name: 'Editor',
        component: EditorView,
        meta: {loginRequire: true},
        children: [
            {
                path: '/editor/file',
                name: 'File',
                component: FileEditorView
            },
            {
                path: '/editor/server',
                name: 'Server',
                component: ServerEditorView
            },
            {
                path: '/editor/dialog',
                name: 'Dialog',
                component: DialogEditorView
            },
            {
                path: '/editor/wiki',
                name: 'Wiki',
                component: WikiEditorView
            }
        ]
    },
]

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes
})

router.beforeEach((to, from, next) => {
    window.scrollTo(0, 0); // Scroll to the top of the page
    if (to.matched.some(function (item) {
        return item.meta.loginRequire;
    })) {
        const loginUser = store.state.user;
        if (loginUser == null || typeof (loginUser) == "undefined"
            || !("username" in loginUser)
            || !loginUser.username || loginUser.username == "") {
            console.log("User not login" + loginUser);
            next("/");
        } else {
            next();
        }
    } else {
        next();
    }
});

export default router

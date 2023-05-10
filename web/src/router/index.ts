import {createRouter, createWebHistory, RouteRecordRaw} from 'vue-router'
import EditorView from "@/views/admin/editor.vue";
import FileEditorView from "@/views/admin/editor/file.vue";
import ServerEditorView from "@/views/admin/editor/server.vue";
import DialogEditorView from "@/views/admin/editor/dialog.vue";
import Home from "@/views/home.vue";
import About from "@/views/about.vue";

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
    next();
});

export default router

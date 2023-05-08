import {createRouter, createWebHistory, RouteRecordRaw} from 'vue-router'
import EditorView from "@/views/EditorView.vue";
import FileEditorView from "@/views/FileEditorView.vue";
import ServerEditorView from "@/views/ServerEditorView.vue";
import DialogEditorView from "@/views/DialogEditorView.vue";

const routes: Array<RouteRecordRaw> = [
    {
        path: '/',
        name: 'main',
        component: EditorView,
    },
    {
        path: '/editor/',
        name: 'editor',
        component: EditorView,
        children: [
            {
                path: '/editor/file',
                component: FileEditorView
            },
            {
                path: '/editor/server',
                component: ServerEditorView
            },
            {
                path: '/editor/dialog',
                component: DialogEditorView
            }
        ]
    },
]

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes
})

export default router

import {createApp} from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import Antd from 'ant-design-vue';
import 'ant-design-vue/dist/antd.css';
import axios from "axios";

axios.interceptors.request.use(function (config) {
    console.log('Requesting: ', config);
    return config;
}, error => {
    return Promise.reject(error);
});
axios.interceptors.response.use(function (response) {
    console.log('Obtaining: ', response);
    return response;
}, error => {
    return Promise.reject(error);
});

createApp(App).use(store).use(router).use(Antd).mount('#app');
axios.defaults.baseURL = process.env.VUE_APP_SERVER;
console.log("Starting with " + process.env.NODE_ENV + " mode with server " + process.env.VUE_APP_SERVER);
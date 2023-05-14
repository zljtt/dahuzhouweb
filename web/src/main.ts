import {createApp} from 'vue'
import App from './views/app.vue'
import router from './router'
import store from './store'
import Antd from 'ant-design-vue';
import 'ant-design-vue/dist/antd.css';
import axios from "axios";

axios.interceptors.request.use(function (config) {
    const token = store.state.user.token;
    if (token && typeof (token) != "undefined" && config.headers) {
        config.headers.token = token;
    }
    console.log('Requesting: ', config);
    return config;
}, error => {
    return Promise.reject(error);
});
axios.interceptors.response.use(function (response) {
    console.log('Obtaining: ', response);
    return response;
}, function (error) {
    if (error.response && error.response.status === 401) {
        console.log('Received 401 response');
        store.commit("clearUser");
        router.push('/home');
    }
    return Promise.reject(error);
});

createApp(App).use(store).use(router).use(Antd).mount('#app');
axios.defaults.baseURL = process.env.VUE_APP_SERVER;
console.log("Starting with " + process.env.NODE_ENV + " mode with server " + process.env.VUE_APP_SERVER);
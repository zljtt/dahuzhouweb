import {createApp} from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import Antd from 'ant-design-vue';
import 'ant-design-vue/dist/antd.css';
import axios from "axios";

createApp(App).use(store).use(router).use(Antd).mount('#app');
axios.defaults.baseURL = process.env.VUE_APP_SERVER;
console.log("Starting with " + process.env.NODE_ENV + " mode with server " + process.env.VUE_APP_SERVER);
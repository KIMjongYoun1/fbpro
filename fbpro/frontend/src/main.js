import { createApp } from 'vue';
import App from './App.vue';
import router from './router';
import axios from 'axios';

// Axios 기본 URL 설정
axios.defaults.baseURL = 'http://localhost:10004'; // Spring 서버 주소

createApp(App)
  .use(router)
  .mount('#app');

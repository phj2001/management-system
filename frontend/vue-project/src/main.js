// src/main.js
import { createApp } from 'vue'
import { createPinia } from 'pinia' // 引入Pinia

import App from './App.vue'
import './assets/main.css' // 引入全局样式

const app = createApp(App)

app.use(createPinia()) // 使用Pinia

app.mount('#app')
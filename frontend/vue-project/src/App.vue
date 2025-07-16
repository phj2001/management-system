<script setup>
import {ref} from 'vue'
const message = ref('点击按钮从后端获取数据')
async function fetchMessage() {
  try {
    const response = await fetch('http://localhost:8080/api/hello')
    const backendMessage = await response.text()
    message.value = backendMessage
  } catch (error) {
    console.error('请求后端数据失败：',error)
    message.value = "获取数据失败，请检查后端服务是否开启!"
  }
}
</script>

<template>
  <div class="container">
    <h1>我的第一个全栈应用</h1>
    <button @click="fetchMessage">向后端请求数据</button>
    <p class="message-display">来自后端的消息:<strong>{{ message }}</strong></p>
    <p>这是一个简单的创建分支，进行更改并且拉取请求通过的示例。</p>
  </div>
</template>

<style scoped>
.container {
  max-width:600px;
  margin:50px auto;
  text-align:center;
  font-family:sans-serif;
}
button {
  padding:10px 20px;
  font-size:16px;
  cursor:pointer;
  margin-bottom:20px;
}
.message-display {
  padding:20px;
  background-color:#00ff55;
  border:1px solid #ccc;
  border-radius:5px;
}
</style>

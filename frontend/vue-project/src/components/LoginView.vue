<!-- src/components/LoginView.vue -->
<script setup>
import { ref } from 'vue'
import { useAuthStore } from '@/stores/auth'
import { LogIn } from 'lucide-vue-next'; // 引入图标

const authStore = useAuthStore()
const username = ref('admin')
const password = ref('password123')

async function handleLogin() {
  await authStore.login(username.value, password.value)
}
</script>

<template>
  <div class="login-container">
    <div class="login-box">
      <h1>欢迎回来</h1>
      <p class="subtitle">请输入您的凭据以继续</p>
      <form @submit.prevent="handleLogin">
        <div class="input-group">
          <input type="text" v-model="username" required placeholder="用户名 (admin)">
        </div>
        <div class="input-group">
          <input type="password" v-model="password" required placeholder="密码 (password123)">
        </div>
        <div v-if="authStore.error" class="error-message">
          {{ authStore.error }}
        </div>
        <button type="submit" :disabled="authStore.loading">
          <span v-if="authStore.loading" class="spinner"></span>
          <LogIn v-else :size="18" />
          <span>{{ authStore.loading ? '登录中...' : '登 录' }}</span>
        </button>
      </form>
    </div>
  </div>
</template>

<style scoped>
/* 这里添加精美的样式 */
.login-container {
  display: flex;
  justify-content: center;
  align-items: center;
  perspective: 1000px;
}

.login-box {
  width: 360px;
  padding: 40px;
  background: var(--glass-bg);
  border-radius: 20px;
  border: 1px solid var(--glass-border);
  backdrop-filter: blur(10px);
  box-shadow: 0 8px 32px 0 rgba(0, 0, 0, 0.37);
  transform-style: preserve-3d;
  transition: transform 0.5s ease;
}

.login-box:hover {
    transform: rotateY(5deg) rotateX(5deg);
}

h1 {
  font-weight: 600;
  font-size: 2.5rem;
  margin-bottom: 10px;
  color: var(--text-color);
  text-shadow: 0 0 10px rgba(255, 255, 255, 0.5);
}

.subtitle {
  color: #c0c0c0;
  margin-bottom: 30px;
}

.input-group {
  margin-bottom: 20px;
}

input {
  width: 100%;
  padding: 12px 15px;
  background: transparent;
  border: 1px solid var(--glass-border);
  border-radius: 8px;
  color: var(--text-color);
  font-size: 1rem;
  transition: all 0.3s ease;
}

input::placeholder {
  color: rgba(255, 255, 255, 0.5);
}

input:focus {
  outline: none;
  border-color: var(--primary-color);
  box-shadow: 0 0 15px rgba(106, 17, 203, 0.5);
}

button {
  width: 100%;
  padding: 12px;
  background: linear-gradient(45deg, var(--primary-color), var(--secondary-color));
  border: none;
  border-radius: 8px;
  color: white;
  font-size: 1.1rem;
  font-weight: 600;
  cursor: pointer;
  transition: all 0.3s ease;
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 10px;
}

button:hover:not(:disabled) {
  transform: translateY(-2px);
  box-shadow: 0 5px 20px rgba(0, 0, 0, 0.4);
}

button:disabled {
  opacity: 0.7;
  cursor: not-allowed;
}

.error-message {
  color: #ff8a80;
  background-color: rgba(255, 0, 0, 0.1);
  padding: 10px;
  border-radius: 5px;
  margin-bottom: 15px;
  font-size: 0.9rem;
}

.spinner {
  width: 18px;
  height: 18px;
  border: 2px solid rgba(255, 255, 255, 0.3);
  border-top-color: #fff;
  border-radius: 50%;
  animation: spin 1s linear infinite;
}

@keyframes spin {
  to { transform: rotate(360deg); }
}
</style>
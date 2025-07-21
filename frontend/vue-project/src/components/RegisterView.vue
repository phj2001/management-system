<!-- src/components/RegisterView.vue -->
<script setup>
import { ref } from 'vue'
import { useAuthStore } from '@/stores/auth'
import { UserPlus } from 'lucide-vue-next';

// 定义emits，用于通知父组件注册成功

const authStore = useAuthStore()
const name = ref('')
const username = ref('')
const password = ref('')
const emit = defineEmits(['register-success', 'go-to-login'])

async function handleRegister() {
  const success = await authStore.register(name.value, username.value, password.value)
  if (success) {
    // 注册成功后，触发事件通知父组件
    emit('register-success');
  }
}
</script>

<template>
  <div class="register-container">
    <div class="register-box">
      <h1>创建账户</h1>
      <p class="subtitle">加入我们，开启新的旅程</p>
      <form @submit.prevent="handleRegister">
        <div class="input-group">
          <input type="text" v-model="name" required placeholder="昵称">
        </div>
        <div class="input-group">
          <input type="text" v-model="username" required placeholder="用户名">
        </div>
        <div class="input-group">
          <input type="password" v-model="password" required placeholder="密码">
        </div>
        <div v-if="authStore.registerError" class="error-message">
          {{ authStore.registerError }}
        </div>
        <button type="submit" :disabled="authStore.loading">
          <span v-if="authStore.loading" class="spinner"></span>
          <UserPlus v-else :size="18" />
          <span>{{ authStore.loading ? '注册中...' : '注 册' }}</span>
        </button>
      </form>
    </div>
  </div>
    <div class="switch-view">
    已有账户？ <a href="#" @click.prevent="emit('go-to-login')">返回登录</a>
  </div>
</template>

<style scoped>
/* 样式可以复用或微调LoginView的样式 */
.register-container {
  display: flex;
  justify-content: center;
  align-items: center;
  perspective: 1000px;
}

.register-box {
  width: 360px;
  padding: 40px;
  background: var(--glass-bg);
  border-radius: 20px;
  border: 1px solid var(--glass-border);
  backdrop-filter: blur(10px);
  box-shadow: 0 8px 32px 0 rgba(0, 0, 0, 0.37);
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
}

input:focus {
  outline: none;
  border-color: var(--primary-color);
  box-shadow: 0 0 15px rgba(106, 17, 203, 0.5);
}

button {
  width: 100%;
  padding: 12px;
  background: linear-gradient(45deg, #28a745, #218838); /* 绿色渐变 */
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

@keyframes spin { to { transform: rotate(360deg); } }

.switch-view {
  margin-top: 20px;
  font-size: 0.9rem;
}
.switch-view a {
  color: var(--primary-color);
  text-decoration: none;
  font-weight: 600;
}
</style>
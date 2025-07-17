<!-- src/components/WelcomeView.vue -->
<script setup>
import { useAuthStore } from '@/stores/auth'
import { LogOut } from 'lucide-vue-next'

const authStore = useAuthStore()

function handleLogout() {
  authStore.logout()
}
</script>

<template>
  <div class="welcome-container">
    <img :src="authStore.user.avatarUrl" alt="User Avatar" class="avatar">
    <h1>欢迎, {{ authStore.user.name }}!</h1>
    <p>你已成功登录我们的全栈应用。</p>
    <button @click="handleLogout">
      <LogOut :size="18" />
      <span>退 出</span>
    </button>
  </div>
</template>

<style scoped>
.welcome-container {
  padding: 40px;
  background: var(--glass-bg);
  border-radius: 20px;
  border: 1px solid var(--glass-border);
  backdrop-filter: blur(10px);
  box-shadow: 0 8px 32px 0 rgba(0, 0, 0, 0.37);
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 15px;
}

.avatar {
  width: 100px;
  height: 100px;
  border-radius: 50%;
  border: 3px solid var(--glass-border);
  box-shadow: 0 0 20px rgba(0, 0, 0, 0.3);
}

h1 {
  font-size: 2.2rem;
  margin: 10px 0 0;
  text-shadow: 0 0 10px rgba(255, 255, 255, 0.5);
}

p {
  color: #c0c0c0;
  font-size: 1.1rem;
}

button {
  margin-top: 20px;
  padding: 10px 30px;
  background: #e91e63; /* Use a different color for logout */
  border: none;
  border-radius: 8px;
  color: white;
  font-size: 1rem;
  font-weight: 600;
  cursor: pointer;
  transition: all 0.3s ease;
  display: flex;
  align-items: center;
  gap: 10px;
}

button:hover {
  transform: translateY(-2px);
  box-shadow: 0 5px 15px rgba(233, 30, 99, 0.4);
}
</style>
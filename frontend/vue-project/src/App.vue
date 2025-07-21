<script setup>
import { ref } from 'vue'; // 引入ref
import { useAuthStore } from '@/stores/auth'
import LoginView from './components/LoginView.vue'
import WelcomeView from './components/WelcomeView.vue'
import RegisterView from './components/RegisterView.vue' // 引入注册组件

const authStore = useAuthStore()

// 'login', 'register'
const currentView = ref('login') // 控制显示登录还是注册页

function switchToRegister() {
  currentView.value = 'register'
}

function switchToLogin() {
  currentView.value = 'login'
}

function handleRegisterSuccess() {
  alert('注册成功！现在请登录。')
  switchToLogin()
}
</script>

<template>
  <main>
    <Transition name="fade" mode="out-in">
      <WelcomeView v-if="authStore.isLoggedIn" />
      <div v-else>
        <Transition name="fade" mode="out-in">
          <LoginView v-if="currentView === 'login'" @go-to-register="switchToRegister" />
          <RegisterView v-else-if="currentView === 'register'" @register-success="handleRegisterSuccess" @go-to-login="switchToLogin" />
        </Transition>
      </div>
    </Transition>
  </main>
</template>

<style scoped>
.fade-enter-active,
.fade-leave-active {
  transition: opacity 0.3s ease;
}

.fade-enter-from,
.fade-leave-to {
  opacity: 0;
}
</style>
<!-- src/App.vue -->
<script setup>
import { useAuthStore } from '@/stores/auth'
import LoginView from './components/LoginView.vue'
import WelcomeView from './components/WelcomeView.vue'

const authStore = useAuthStore()
</script>

<template>
  <main>
    <Transition name="fade" mode="out-in">
      <WelcomeView v-if="authStore.isLoggedIn" />
      <LoginView v-else />
    </Transition>
  </main>
</template>

<style scoped>
.fade-enter-active,
.fade-leave-active {
  transition: opacity 0.5s ease;
}

.fade-enter-from,
.fade-leave-to {
  opacity: 0;
}
</style>
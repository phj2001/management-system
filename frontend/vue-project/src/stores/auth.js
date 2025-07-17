import { defineStore } from "pinia"
import {ref,computed} from "vue"

export const useAuthStore = defineStore('auth', () => {
    const user = ref(null)
    const loading = ref(false)
    const error = ref(null)

    const isLoggedIn = computed(() => !!user.value)

    async function login(username,password) {
        loading.value = true
        error.value = null
        try {
            const response = await fetch('http://localhost:8080/api/login', {
                method:'POST',
                headers: {
                    'Content-Type':'application/json',
                },
                body:JSON.stringify({username,password}),
            })

            if(!response.ok) {
                const errorMessage = await response.text();
                throw new Error(errorMessage || '登录失败');
            }

            user.value = await response.json();
        } catch (e) {
            error.value = e.message
            console.error('登录请求失败：',e)
        } finally {
            loading.value = false
        }
    }

    function logout() {
        user.value = null
    }

    return {user,loading,error,isLoggedIn,login,logout}
})
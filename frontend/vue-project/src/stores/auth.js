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

      const registerError = ref(null) // 新增一个专门用于注册的错误状态

  // --- 新增注册 Action ---
  async function register(name, username, password) {
    loading.value = true
    error.value = null // 清空通用错误
    registerError.value = null // 清空注册错误

    try {
      const response = await fetch('http://localhost:8080/api/register', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ name, username, password }),
      })

      const data = await response.text(); // 先获取响应体文本

      if (!response.ok) {
        throw new Error(data || '注册失败');
      }

      // 注册成功后，可以选择自动登录
      // 这里我们为了流程清晰，只返回成功状态，让用户自己去登录
      // 如果想自动登录，可以解析JSON，然后 user.value = JSON.parse(data)
      return true; // 表示注册成功

    } catch (e) {
      registerError.value = e.message;
      console.error('注册请求失败:', e);
      return false; // 表示注册失败
    } finally {
      loading.value = false;
    }
  }

  // logout action ...

  return { user, loading, error, registerError, isLoggedIn, login, logout, register }

})
<template>
    <div>
      <h2>사용자 추가</h2>
      <form @submit.prevent="submitForm">
        <input v-model="user.name" placeholder="이름" />
        <input v-model="user.email" placeholder="이메일" />
        <button type="submit">저장</button>
      </form>
    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        user: {
          name: '',
          email: ''
        }
      };
    },
    methods: {
      async submitForm() {
        try {
          const response = await fetch('/users', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(this.user)
          });
          if (response.ok) {
            this.$router.push('/user-list');  // 사용자 목록 페이지로 리디렉션
          }
        } catch (error) {
          console.error("Error:", error);
        }
      }
    }
  };
  </script>
  
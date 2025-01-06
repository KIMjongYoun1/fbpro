<template>
    <div>
      <h2>사용자 목록</h2>
      <ul>
        <li v-for="user in users" :key="user.id">{{ user.name }} - {{ user.email }}</li>
      </ul>
    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        users: []
      };
    },
    async mounted() {
      try {
        const response = await fetch('/users');
        if (response.ok) {
          this.users = await response.json();
        }
      } catch (error) {
        console.error("Error:", error);
      }
    }
  };
  </script>
  
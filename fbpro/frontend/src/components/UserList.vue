<template>
  <div>
    <h1>사용자 목록</h1>
    <ul v-if="users.length > 0">
      <li v-for="user in users" :key="user.userId">{{ user.fullName }}</li>
    </ul>
    <p v-else>사용자 데이터가 없습니다.</p>
  </div>
</template>

<script>
// axios를 사용하기 위해 import
import axios from 'axios';

export default {
  data() {
    return {
      users: [], // 사용자 목록을 저장할 배열
    };
  },
  methods: {
    async fetchUsers() {
      try {
        // axios를 사용하여 API 호출
        const response = await axios.get("/api/users");
        this.users = response.data; // 응답 받은 데이터를 users 배열에 저장
      } catch (error) {
        console.error("사용자 목록 로드 실패:", error.message); // 오류 처리
      }
    },
  },
  mounted() {
    this.fetchUsers(); // 컴포넌트가 마운트되면 데이터를 가져옵니다.
  },
};
</script>

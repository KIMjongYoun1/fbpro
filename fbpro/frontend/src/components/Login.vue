<template>
    <div>
        <h2>로그인</h2>
        <form @submit.prevent="loginUser">
            <div>
                <label for="userId">ID</label>
                <input id="userId" v-model="userId" placeholder="ID" required />
            </div>
            <div>
                <label for="password">password</label>
                <input id="password" v-model="password" type="password" placeholder="Password" required />
            </div>
            <button type="submit">LogIn</button>
        </form>
    </div>
</template>

<script>
import axios from '@/axios';  // 커스텀 axios 인스턴스를 불러오기

export default {
    name:"loginFrom",
  data() {
    return {
      userId: '',
      password: '',
    };
  },
  methods: {
    async loginUser() {
      try {
        const response = await axios.post('/api/users/login', {
          userId: this.userId,
          password: this.password,
        });

        if (response.status === 200) {
          const token = response.data.replace("Bearer ", ""); // 'Bearer ' 제거하고 토큰만 추출
          localStorage.setItem('jwtToken', token); // JWT 토큰 저장
          alert('로그인 성공');
          this.$router.push('/dashboard');
        } else {
          alert('아이디 또는 비밀번호가 잘못되었습니다.');
        }
      } catch (error) {
        console.error('Error during login:', error);
        alert('서버 오류');
      }
    }
  }
};
</script>

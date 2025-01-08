<template>
    <div>
      <h2>회원가입</h2>
      <UserForm ref="userForm" :submitLabel="'가입하기'" @submit="registerUser" />
    </div>
  </template>
  
  <script>
  import UserForm from "@/components/UserForm.vue";
  
  export default {
    name: 'RegisterPage',
    components: { UserForm },
    data() {
      return {
        isSubmitting: false,  // 요청 중인지 확인하는 변수
      };
    },
    methods: {
      async registerUser(userData) {
        if (this.isSubmitting) return;  // 요청 중이면 중복 요청 방지
        this.isSubmitting = true;  // 요청 시작 시 플래그 설정
  
        try {
          const response = await fetch('http://localhost:10004/api/users', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(userData),
          });
  
          if (response.ok) {
            alert('회원가입 완료');
            this.$router.push('/login');  // 회원가입 후 로그인 페이지로 이동
          } else {
            const errorText = await response.text();
            alert('다시 시도해주세요: ' + errorText);
          }
        } catch (error) {
          console.error('Error', error);
          alert('서버를 확인해주세요 서버 오류');
        } finally {
          this.isSubmitting = false;  // 요청 종료 후 플래그 해제
  
          // 폼 초기화 (UserForm 컴포넌트의 데이터 초기화)
          this.$refs.userForm.form = { user_id: '', full_name: '', email: '', password: '' };
        }
      },
    },
  };
  </script>
  
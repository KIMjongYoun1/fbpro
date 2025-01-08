<template>
  <form @submit.prevent="handleSubmit">
    <div>
      <label for="userId">로그인 ID</label>
      <input id="userId" v-model="form.userId" placeholder="Id를 입력하세요" required />
    </div>
    <div>
      <label for="name">이름</label>
      <input id="name" v-model="form.fullName" placeholder="이름" />
    </div>
    <div>
      <label for="email">이메일</label>
      <input id="email" v-model="form.email" type="email" placeholder="이메일" />
    </div>
    <div>
      <label for="password">비밀번호</label>
      <input id="password" v-model="form.password" type="password" placeholder="비밀번호입력" required />
    </div>
    <button type="submit">{{ submitLabel }}</button>
  </form>
</template>

<script>
export default {
  props: {
    initialData: {
      type: Object,
      default: () => ({
        user_id: '',
        full_name: '',
        email: '',
        password: '',
      }),
    },
    submitLabel: {
      type: String,
      default: '저장'
    }
  },
  data() {
    return {
      form: { ...this.initialData },  // form을 initialData로 초기화
    };
  },
  methods: {
    handleSubmit() {
      this.$emit('submit', this.form);  // 부모 컴포넌트로 데이터 전달
      // 폼 제출 후 초기화
      this.form = { user_id: '', full_name: '', email: '', password: '' };
    }
  }
};
</script>

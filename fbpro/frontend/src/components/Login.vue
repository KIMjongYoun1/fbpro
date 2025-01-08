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
    export default {
        name: 'loginForm',
        data() {
            return {
                userId: '',
                password: '',
            };
        },
        methods: {
            async loginUser() {
                console.log("아이디: ", this.userId);
                console.log("비밀번호: ", this.password);
                try {
                    const response = await fetch('http://localhost:10004/api/users/login', {
                        method: 'POST',
                        headers: { 'Content-Type': 'application/json' },
                        body: JSON.stringify({
                            userId: this.userId,
                            password: this.password,
                        }),
                    });
                    if (response.ok) {
                        alert('로그인 성공');
                        this.$router.push('/dashboard');
                    } else {
                        alert('다시 시도해 주세요', );
                    }

                } catch (error) {
                    console.error('Error', error);
                    alert('서버오류');
                }

            },
        },
    };
</script>
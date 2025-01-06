<template>
    <div>
        <h2>스케쥴 근무 코드 추가</h2>
        <form @submit.prevent="handleSubmit">
            <label>스케쥴 코드: </label>
            <input type="text" v-model="schedule.code" required />

            <label>근무 유형 설명: </label>
            <input type="text" v-model="schedule.description" required />

            <label>시작 시간: </label>
            <input type="time" v-model="schedule.startTime" required />

            <label>종료 시간: </label>
            <input type="time" v-model="schedule.endTime" required />

            <button type="submit">저장</button>
            <button type="button" @click="resetForm">초기화</button>
        </form>

        <h3>스케쥴 코드 목록</h3>
        <ul>
            <li v-for="shift in shiftTypes" :key="shift.code">
                {{ shift.code }}: {{ shift.startTime }} ~ {{ shift.endTime }} ({{ shift.description }})
            </li>
        </ul>
    </div>
</template>

<script>
import axios from 'axios';

export default {
    data() {
        return {
            schedule: {
                code: '', // 스케쥴 코드 (예: A1, B2)
                description: '', // 근무 유형 설명
                startTime: '', // 근무 시작 시간
                endTime: '', // 근무 종료 시간
            },
            shiftTypes: [], // 스케쥴 코드 목록
        };
    },
    methods: {
        // 스케쥴 코드 추가
        async handleSubmit() {
              // 빈 값일 경우 빈 문자열로 처리
    if (!this.schedule.description) {
        this.schedule.description = '';  // 빈 값 처리
    }

            console.log("전송할 데이터:", this.schedule);   // 값이 정상적으로 입력되는지 확인

if (!this.schedule.code) {
    alert("스케쥴 코드를 입력해주세요.");
    return;
}


            try {
                await axios.post('/api/shift-types', this.schedule);
                alert('스케쥴 코드가 추가되었습니다.');
                this.fetchShiftTypes(); // 목록 갱신
                this.resetForm();
            } catch (error) {
                console.error('스케쥴 코드 추가 실패:', error);
            }
        },
        // 스케쥴 코드 목록 불러오기
        async fetchShiftTypes() {
            try {
                const response = await axios.get('/api/shift-types');
                this.shiftTypes = response.data;
            } catch (error) {
                console.error('스케쥴 코드 목록 로드 실패:', error);
            }
        },
        // 폼 초기화
        resetForm() {
            this.schedule = { code: '', description: '', startTime: '', endTime: '' };
        },
    },
    mounted() {
        this.fetchShiftTypes(); // 컴포넌트 마운트 시 스케쥴 코드 목록 로드
    },
};
</script>

<style scoped>
form {
    margin-bottom: 20px;
}
form label {
    display: block;
    margin: 10px 0 5px;
    font-weight: bold;
}
form input {
    margin-bottom: 15px;
    padding: 8px;
    width: 100%;
    max-width: 300px;
    border: 1px solid #ccc;
    border-radius: 4px;
}
button {
    margin-right: 10px;
    padding: 8px 16px;
    background-color: #007bff;
    color: white;
    border: none;
    border-radius: 5px;
    cursor: pointer;
}
button:hover {
    background-color: #0056b3;
}
ul {
    list-style: none;
    padding: 0;
}
li {
    margin: 5px 0;
}
</style>

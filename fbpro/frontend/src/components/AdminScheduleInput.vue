<template>
    <div>
        <h2>스케줄 배정</h2>
        <form @submit.prevent="submitSchedule">
            <!-- 사용자 선택 -->
            <div>
                <label for="userId">근로자</label>
                <select v-model="schedule.userId" id="userId" required>
                    <option v-for="user in users" :key="user.userId" :value="user.userId">
                        {{ user.name }} ({{ user.userId }})
                    </option>
                </select>
            </div>

            <!-- 쉬프트 코드 선택 -->
            <!-- 쉬프트 코드 선택 -->
            <div>
                <label for="shiftCode">쉬프트 코드</label>
                <select v-model="schedule.shiftCode" id="shiftCode" name="shiftCode" required>
                    <option v-for="shift in shiftTypes" :key="shift.code" :value="shift.code">
                        {{ shift.description }} ({{ shift.code }})
                    </option>
                </select>
            </div>


            <!-- 스케줄 코드 입력 -->
            <div>
                <label for="scheduleCode">스케쥴 코드</label>
                <input v-model="schedule.scheduleCode" id="scheduleCode" name="scheduleCode" type="text" required />
            </div>

            <!-- 생성 시간 입력 -->
            <div>
                <label for="createdAt">생성 시간</label>
                <input v-model="schedule.createdAt" id="createdAt" name="createdAt" type="datetime-local" required />
            </div>

            <!-- 제출 버튼 -->
            <button type="submit">배정</button>
        </form>
    </div>
</template>

<script>
    import axios from 'axios';

    export default {
        data() {
            return {
                schedule: {
                    scheduleCode: "",
                    userId: "",
                    shiftCode: "", // 초기값을 빈 문자열로 설정
                    createdAt: "",
                },
                users: [], // 사용자 목록
                shiftTypes: [], // 쉬프트 타입 목록
            };
        },
        methods: {
            // 사용자 목록 가져오기
            async fetchUsers() {
                try {
                    const response = await axios.get("/api/users"); // 사용자 API 호출
                    console.log('사용자 목록:', response.data);
                    this.users = response.data;
                } catch (error) {
                    console.error("사용자 목록 로드 실패:", error.message);
                }
            },

            // 쉬프트 타입 목록 가져오기
            // 쉬프트 타입 목록 가져오기
            async fetchShiftTypes() {
                try {
                    const response = await axios.get("/api/shift-types/all");
                    this.shiftTypes = response.data;

                    console.log("Shift Types:", this.shiftTypes); // 확인: shiftTypes 배열에 데이터가 제대로 들어왔는지
                    console.log("Current shiftCode:", this.schedule.shiftCode); // 확인: schedule.shiftCode에 값이 제대로 들어왔는지
                } catch (error) {
                    console.error("쉬프트 타입 로드 실패:", error);
                }
            }
            ,

            // 스케줄 데이터 제출
            async submitSchedule() {
                // 쉬프트 코드가 선택되지 않으면 경고 표시
                if (!this.schedule.shiftCode) {
                    alert("쉬프트 코드를 선택하세요.");
                    return; // 빈 값이면 제출하지 않음
                }

                console.log("제출할 데이터:", this.schedule); // 데이터 확인

                try {
                    const response = await axios.post("/schedule/add", this.schedule);  // API 호출
                    if (response.status === 200) {
                        alert("스케줄 추가 성공");
                        this.resetForm();
                    } else {
                        alert("스케줄 배정 실패");
                    }
                } catch (error) {
                    console.error("스케줄 배정 요청 실패:", error);
                }
            },

            // 폼 초기화
            resetForm() {
                this.schedule = {
                    scheduleCode: "",
                    userId: "",
                    shiftCode: "",
                    createdAt: "",
                };
            },
        },
        async mounted() {
            // 컴포넌트 로드 시 사용자 목록과 쉬프트 타입 목록 가져오기
            await this.fetchUsers();
            await this.fetchShiftTypes();
        },
    };
</script>

<style scoped>
    /* 스타일 */
    form {
        max-width: 400px;
        margin: 0 auto;
    }

    div {
        margin-bottom: 20px;
    }

    label {
        display: block;
        margin-bottom: 5px;
        font-weight: bold;
    }

    input,
    select,
    button {
        width: 100%;
        padding: 10px;
        margin-top: 5px;
        font-size: 1em;
    }

    button {
        background-color: #007bff;
        color: #fff;
        border: none;
        cursor: pointer;
        font-size: 1em;
        text-align: center;
    }

    button:hover {
        background-color: #0056b3;
    }
</style>
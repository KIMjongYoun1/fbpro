<template>
    <div>
        <h1>스케쥴 상세</h1>
        <div v-if="schedule">
            <p><strong>근무자 ID:</strong> {{ schedule.userId }}</p>
            
            <p><strong>쉬프트 코드:</strong> {{ schedule.shiftCode }}</p>
            <p><strong>날짜:</strong> {{ schedule.createdAt }}</p>

        </div>
        <div v-else>
            <p>스케줄 정보를 불러오는 중입니다...</p>
        </div>
    </div>
</template>

<script>
import axios from 'axios';

export default {
    name: 'ScheduleDetail',
    props: {
        scheduleCode: {
            type: String,
            required: true,
        },
    },
    data() {
        return {
            schedule: null,
        };
    },
    created() {
        console.log('받은 scheduleCode:', this.scheduleCode);
        this.fetchScheduleDetails();
    },
    methods: {
        async fetchScheduleDetails() {
            console.log('API 호출 시작');
            try {
                const response = await axios.get(`/schedule/details/${this.scheduleCode}`);
                console.log('API 응답 데이터:', response.data);
                this.schedule = response.data; // 또는 response.data.data, 필요에 따라 수정
            } catch (error) {
                console.error('API 호출 중 오류 발생:', error);
            }
        },
    },
};
</script>

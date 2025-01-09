<template>
    <div>
      <h1>근무 유형 목록</h1>
      <ul v-if="shiftTypes.length > 0">
        <li v-for="shift in shiftTypes" :key="shift.shiftCode">
          <strong>{{ shift.shiftCode }}</strong> - {{ shift.description }}<br>
          Start Time: {{ shift.startTime }}<br>
          End Time: {{ shift.endTime }}<br><br>
        </li>
      </ul>
      <p v-else>근무 유형 데이터가 없습니다.</p>
    </div>
  </template>
  
  <script>
  // axios를 사용하기 위해 import
  import axios from "axios";
  
  export default {
    data() {
      return {
        shiftTypes: [], // ShiftType 목록을 저장할 배열
      };
    },
    methods: {
      // 쉬프트 타입 목록을 가져오는 메서드
      async fetchShiftTypes() {
        try {
          const response = await axios.get("/api/shift-types/all"); // API 요청
          this.shiftTypes = response.data; // 응답 받은 데이터를 shiftTypes 배열에 저장
        } catch (error) {
          console.error("근무 유형 목록 로드 실패:", error.message);
        }
      },
    },
    mounted() {
      this.fetchShiftTypes(); // 컴포넌트가 마운트되면 데이터를 가져옵니다.
    },
  };
  </script>
  
  <style scoped>
  /* 스타일 */
  ul {
    list-style-type: none;
    padding: 0;
  }
  
  li {
    margin-bottom: 15px;
  }
  </style>
  
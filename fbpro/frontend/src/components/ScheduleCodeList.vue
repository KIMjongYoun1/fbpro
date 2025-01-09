<template>
    <div>
      <h2>스케줄 코드 목록</h2>
      <ul>
        <li v-for="schedule in schedules" :key="schedule">
          {{ schedule }}
        </li>
      </ul>
    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        schedules: [], // 스케줄 데이터를 저장할 배열
      };
    },
    methods: {
      async fetchSchedules() {
        try {
          const response = await fetch("http://localhost:8080/schedule/code");
          if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
          }
          this.schedules = await response.json(); // 데이터를 배열에 저장
        } catch (error) {
          console.error("스케줄 데이터를 로드하는 데 실패했습니다:", error);
        }
      },
    },
    mounted() {
      this.fetchSchedules(); // 컴포넌트 로드 시 데이터 가져오기
    },
  };
  </script>
  
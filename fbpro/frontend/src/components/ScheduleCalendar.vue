<template>
  <div>
    <h2>근무 스케쥴 관리</h2>
    <!-- FullCalendar가 렌더링될 자리 -->
    <div ref="calendar"></div> <!-- ref를 통해 FullCalendar를 렌더링 -->
  </div>
</template>

<script>
import { ref, onMounted } from 'vue'; // ref와 onMounted만 import
import { Calendar } from '@fullcalendar/core'; // FullCalendar import
import dayGridPlugin from '@fullcalendar/daygrid'; // dayGrid 플러그인 import
import axios from 'axios'; // Axios import

export default {
  name: 'ScheduleCalendar',
  setup() {
    const calendar = ref(null); // 캘린더 DOM 참조
    const events = ref([]); // 이벤트 데이터를 저장할 변수

    onMounted(async () => {
      try {
        const apiUrl = 'http://localhost:10004/schedule/List'; // Spring API 경로
        console.log(`Calling API: ${apiUrl}`); // API 호출 경로 로그
        const response = await axios.get(apiUrl); // Axios로 API 호출
        console.log('API Response Data:', response.data);
        events.value = response.data; // API 데이터를 FullCalendar 이벤트에 저장

        // FullCalendar 초기화
        if (calendar.value) {
          const calendarInstance = new Calendar(calendar.value, {
            plugins: [dayGridPlugin], // 플러그인 추가
            initialView: 'dayGridMonth', // 초기 뷰
            events: events.value, // API에서 가져온 이벤트 데이터
          });
          calendarInstance.render(); // 캘린더 렌더링
        } else {
          console.error('캘린더 DOM 요소를 찾을 수 없습니다.');
        }
      } catch (error) {
        console.error('API 호출 오류:', error); // 오류 로그 출력
      }
    });

    return { calendar }; // setup에서 ref 반환
  },
};

</script>

<style scoped>
/* 스타일을 이 컴포넌트에만 적용할 수 있게 scoped 추가 */
</style>

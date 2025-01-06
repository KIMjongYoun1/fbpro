<template>
  <div>
    <h2>근무 스케쥴 관리</h2>
    <!-- 풀캘린더 랜더링 자리-->
    <div ref="calendar"></div> <!-- ref를 통해 FullCalendar를 렌더링 -->
  </div>
</template>

<script>
import { ref, onMounted } from 'vue';  // ref와 onMounted를 import
import { Calendar } from '@fullcalendar/core';  // FullCalendar import
import dayGridPlugin from '@fullcalendar/daygrid';  // dayGrid 플러그인 import

export default {
  name: 'ScheduleCalendar',
  setup() {
    const calendar = ref(null);  // ref를 사용하여 calendar 변수 선언

    onMounted(() => {
      // 캘린더가 DOM에 마운트되었을 때 FullCalendar 초기화
      if (calendar.value) {
        const calendarInstance = new Calendar(calendar.value, {
          plugins: [dayGridPlugin],  // 플러그인 설정
          initialView: 'dayGridMonth',  // 초기 뷰 설정
          events: [
            { title: '근무: 오전', date: '2025-01-01' },
            { title: '근무: 오후', date: '2025-01-01' },
          ],  // 이벤트 데이터
        });
        calendarInstance.render();  // 캘린더 렌더링
      } else {
        console.error('캘린더 DOM 요소를 찾을 수 없습니다.');
      }
    });

    return { calendar };  // setup()에서 ref 객체를 반환
  },
};
</script>

<style scoped>
  /* 스타일을 이 컴포넌트에만 적용할 수 있게 scoped 추가 */
</style>

import { createRouter, createWebHistory } from 'vue-router';

// 각 컴포넌트 임포트

import ScheduleCalendar from '../components/ScheduleCalendar.vue';  // 상대 경로 수정
import UserForm from '../components/UserForm.vue';  // 상대 경로 수정
import UserList from '../components/UserList.vue';  // 상대 경로 수정
import ScheduleManager from '../components/ScheduleManager.vue';
import Register from '../components/Register.vue'; // 상대 경로로 임포트
import login from '../components/Login.vue'; // 상대 경로로 임포트
import AdminScheduleInput from "../components/AdminScheduleInput.vue"; // 상대 경로
import ScheduleCodeList from "../components/ScheduleCodeList.vue";
import ShiftList from "../components/ShiftList.vue";




const routes = [
  {
    path:"/shift-types/all",
    name: "shift-types/all",
    component: ShiftList,
  },
  {
    path: "/schedule/code",
    name: "ScheduleCodeList",
    component: ScheduleCodeList,
  },
  {
    path: "/schedule/add", // URL 경로
    name: "ScheduleAdd", // 라우터 이름 (선택사항)
    component: AdminScheduleInput, // 렌더링할 컴포넌트
  },
  {
    path: '/users/login',
    name: 'loginForm',
    component: login // 로그인 페이지
  },
  {
    path: '/users/register',
    name: 'RegisterPage',
    component: Register // 회원가입 페이지
  },
  {
    path: '/',
    name: 'home',
    component: ScheduleCalendar // 홈 페이지
  },
  {
    path: '/schedule',
    name: 'schedule',
    component: ScheduleCalendar // 일정 페이지
  },
  {
    path: '/user-form',
    name: 'user-form',
    component: UserForm // 사용자 폼
  },
  {
    path: '/users',  // 사용자 목록 페이지 경로
    name: 'user-list',
    component: UserList // 사용자 목록 컴포넌트
  },
  {
    path: '/schedule-manager',
    name: 'schedule-manager',
    component: ScheduleManager,  // 스케줄 관리 페이지
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes
});

export default router;

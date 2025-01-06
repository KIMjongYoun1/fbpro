import { createRouter, createWebHistory } from 'vue-router';

// 각 컴포넌트 임포트
import ScheduleCalendar from '../components/ScheduleCalendar.vue';  // 상대 경로 수정
import UserForm from '../components/UserForm.vue';  // 상대 경로 수정
import UserList from '../components/UserList.vue';  // 상대 경로 수정
import ScheduleManager from '../components/ScheduleManager.vue';


const routes = [
  {
    path: '/',
    name: 'home',
    component: ScheduleCalendar
  },
  {
    path: '/schedule',
    name: 'schedule',
    component: ScheduleCalendar
  },
  {
    path: '/user-form',
    name: 'user-form',
    component: UserForm
  },
  {
    path: '/user-list',
    name: 'user-list',
    component: UserList
  },
  {
    path: '/schedule-manager',
    name: 'schedule-manager',
    component: ScheduleManager,
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes
});

export default router;

import axios from 'axios';

// Axios 인스턴스 생성
const instance = axios.create({
  baseURL: 'http://localhost:10004',  // 서버 URL
  timeout: 10000,  // 타임아웃 설정
});

// 요청 인터셉터 설정: 모든 요청에 JWT 토큰 자동 추가
instance.interceptors.request.use(
  (config) => {
    const token = localStorage.getItem('jwtToken');  // 로컬스토리지에서 JWT 토큰을 가져옴
    if (token) {
      config.headers['Authorization'] = `Bearer ${token}`;  // JWT 토큰을 Authorization 헤더에 추가
    }
    return config;  // 토큰을 추가한 후, 요청을 반환
  },
  (error) => {
    return Promise.reject(error);  // 오류 처리
  }
);

export default instance;

<template>
  <div id="app">
    <header>
      <Header />  <!-- 헤더 컴포넌트 -->
    </header>
    <router-view></router-view>  <!-- 라우팅된 컴포넌트 표시 -->
    <footer>
      <Footer />  <!-- 푸터 컴포넌트 -->
    </footer>
  </div>
</template>

<script>
import Header from './components/Header.vue';  // 헤더 컴포넌트 임포트
import Footer from './components/Footer.vue';  // 푸터 컴포넌트 임포트

export default {
  name: 'App',
  components: {
    Header,
    Footer
  }
};
</script>

<style>
/* 스타일 추가 */
</style>

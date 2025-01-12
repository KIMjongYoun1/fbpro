package fbpro.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import fbpro.utill.JwtUtil;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	private final JwtUtil jwtUtil;

	public SecurityConfig(JwtUtil jwtUtil) {
		this.jwtUtil = null;
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	    http
	        .cors().configurationSource(corsConfigurationSource())  // CORS 설정
	        .and()
	        .authorizeRequests()
	            .requestMatchers("/**").permitAll()  // 모든 요청에 대해 인증 없이 접근 허용
	        .and()
	        .httpBasic().disable()  // 기본 HTTP 인증 비활성화
	        .formLogin().disable()  // 폼 로그인 비활성화
	        .csrf().disable()  // CSRF 비활성화 (API에서는 보통 비활성화)
	        .addFilterBefore(new JwtFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class);  // JWT 필터 추가

	    return http.build();
	}


	@Bean
	public InMemoryUserDetailsManager userDetailsManager() {
		return new InMemoryUserDetailsManager(User.withUsername("user").password("{noop}password") // 개발 환경에서만 사용, 평문
																									// 패스워드
				.roles("USER").build());
	}

	@Bean
	public UrlBasedCorsConfigurationSource corsConfigurationSource() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true); // 자격 증명 허용
		config.addAllowedOrigin("http://localhost:8080"); // 프론트엔드 URL (예: Vue.js)
		config.addAllowedHeader("*"); // 모든 헤더 허용
		config.addAllowedMethod("*"); // 모든 HTTP 메서드 허용 (GET, POST, PUT 등)
		source.registerCorsConfiguration("/**", config);
		return source;
	}

}

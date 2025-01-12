package fbpro.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.filter.OncePerRequestFilter;

import fbpro.utill.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtFilter extends OncePerRequestFilter {

	private final JwtUtil jwtUtil;
	// 고정된 시크릿 키 (32바이트 이상)
	@Value("${jwt.secret-key}")
	private String SECRET_KEY; // 어플리케이션 프로퍼티에서 주입받도록 설정

	public JwtFilter(JwtUtil jwtUtil) {
	    this.jwtUtil = jwtUtil;  // jwtUtil을 정상적으로 초기화
	}


	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
	        throws ServletException, IOException {
	    System.out.println("JwtFilter executed: " + request.getRequestURI()); // 로그 추가

	    // 인증을 하지 않도록 처리
	    filterChain.doFilter(request, response);  // 그냥 지나가게 함
	}




}

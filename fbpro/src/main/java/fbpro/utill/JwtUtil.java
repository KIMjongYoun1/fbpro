package fbpro.utill;

import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

	private final Key secretKey;
	private final long expirationTime;

	// JwtProperties를 주입받아 초기화
	public JwtUtil(JwtProperties jwtProperties) {
		System.out.println("Secret Key: " + jwtProperties.getSecretKey());
		System.out.println("Secret Key Bytes Length: " + jwtProperties.getSecretKey().getBytes().length);
		System.out.println("Expiration Time: " + jwtProperties.getExpirationTime());

		if (jwtProperties.getSecretKey() == null || jwtProperties.getSecretKey().isEmpty()) {
			throw new IllegalArgumentException("Secret key cannot be null or empty");
		}

		this.secretKey = Keys.hmacShaKeyFor(jwtProperties.getSecretKey().getBytes());
		this.expirationTime = jwtProperties.getExpirationTime();
	}

	public String generateToken(String userId) {
		try {
			return Jwts.builder().setSubject(userId).setIssuedAt(new Date())
					.setExpiration(new Date(System.currentTimeMillis() + expirationTime)).signWith(secretKey).compact();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("토큰 생성 실패: " + e.getMessage());
		}
	}

	public boolean validateToken(String token) {
		try {
			Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public String extractUserId(String token) {
		try {
			return Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody().getSubject();
		} catch (Exception e) {
			throw new RuntimeException("유효하지 않은 토큰입니다.", e);
		}
	}
}

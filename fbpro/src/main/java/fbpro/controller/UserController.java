package fbpro.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fbpro.service.UserService;
import fbpro.vo.UserVO;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/users")
public class UserController {

	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {

		this.userService = userService;
	}

	// 유저 전체조회
	@GetMapping
	public List<UserVO> getAllUser() {
		return userService.getAllUsers();
	}

	// 특정 사용자 조회
	@GetMapping("/{userId}")
	public UserVO getUserById(@PathVariable String userId) {
		return userService.getUserById(userId);
	}

	// 유저추가
	@PostMapping
	public ResponseEntity<?> insertUser(@RequestBody UserVO user) {
		System.out.println("Received User Data: " + user); // 로그 출력
		try {
			UserVO savedUser = userService.insertUser(user); // 사용자 저장
			return ResponseEntity.status(HttpStatus.CREATED).body(savedUser); // 201 응답
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("사용자 추가 실패: " + e.getMessage());
		}
	}

	// 로그인
	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@RequestBody Map<String, String> loginData) {
		
		String userId = loginData.get("userId");
		String password = loginData.get("password");
		
		System.out.println("Received login request: userId=" + userId + ", password=" + password);  // 로그 찍기

		try {
			UserVO user = userService.authenticateUser(userId, password);
			if (user != null) {
				return ResponseEntity.ok(user);
			} else {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("잘못된사용자");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버에러" + e.getMessage());
			
		}
	}

	// 유저정보 업데이트
	@PutMapping("/{userId}")
	public UserVO updateUser(@PathVariable String userId, UserVO user) {
		user.setUserId(userId); // PathVariable로 받은 userId
		return userService.upDateUser(user);
	}

	// 유저 삭제
	@DeleteMapping("/{userId}")
	public void deleteUser(@PathVariable String userId) {
		userService.deleteUser(userId);
	}

}

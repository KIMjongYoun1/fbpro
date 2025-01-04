package fbpro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

@RestController
@RequestMapping("/users")
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
	public UserVO insertUser(@RequestBody UserVO user) {
		return userService.insertUser(user);
	}
	
	// 유저정보 업데이트
	@PutMapping("/{userId}")
	public UserVO updateUser(@PathVariable String userId, UserVO user) {
		user.setUserId(userId); // PathVariable로 받은 userId 
		return userService.upDateUser(user);
	}
	
	//유저 삭제
	@DeleteMapping("/{userId}")
	public void deleteUser(@PathVariable String userId) {
		userService.deleteUser(userId);
	}
	
}

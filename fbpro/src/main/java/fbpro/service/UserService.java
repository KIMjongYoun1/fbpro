package fbpro.service;

import java.util.List;

import org.springframework.stereotype.Service;

import fbpro.dao.UserDAO;
import fbpro.vo.UserVO;

@Service
public class UserService {
	
	
	private final UserDAO userDAO;
	
	//생성자 주입방식
	public UserService(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	// 모든사용자 조회
	public List<UserVO> getAllUsers(){
		return userDAO.findAllUsers();
	}
	
	// 유저 아이디로조회(특정사용자)
	public UserVO getUserById(String userId) {
		return userDAO.findUserById(userId);
				
	}
	
	// 유저 추가
	public UserVO insertUser(UserVO user) {
		return userDAO.insertUser(user);
	}
	
	// 정보업데이트
	public UserVO upDateUser(UserVO user) {
		return userDAO.upDateUser(user);
	}
	
	// 유저 삭제
	public void deleteUser(String userId) {
		userDAO.deleteUser(userId);
	}
	
}

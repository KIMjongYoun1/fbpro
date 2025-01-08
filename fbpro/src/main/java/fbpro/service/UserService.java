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
	    userDAO.insertUser(user); // 데이터 삽입
	    return userDAO.findUserById(user.getUserId()); // 삽입된 데이터 조회 후 반환
	}
	
	// 정보업데이트
	public UserVO upDateUser(UserVO user) {
		return userDAO.upDateUser(user);
	}
	
	// 유저 삭제
	public void deleteUser(String userId) {
		userDAO.deleteUser(userId);
	}
	
	// 로그인 검증
	public UserVO authenticateUser(String userId, String password) {
	    // 아이디로 사용자 조회
	    UserVO user = userDAO.findUserById(userId);
	    
	    if (user != null) {
	        // 비밀번호를 평문으로 비교
	        if (user.getPassword().equals(password)) {
	            return user;  // 비밀번호가 일치하면 사용자 정보 반환
	        } else {
	            return null;  // 비밀번호 불일치
	        }
	    }
	    return null;  // 사용자 아이디 불일치
	}
	
}

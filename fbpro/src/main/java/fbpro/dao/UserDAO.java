package fbpro.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import fbpro.vo.UserVO;

@Mapper
public interface UserDAO {
	
	//모든사용자 조회
	List<UserVO> findAllUsers();
	
	// userId로 조회
	public UserVO findUserById(String userId);
	
	//사용자 추가
	public UserVO insertUser(UserVO user);
	
	//사용자 업데이트
	public UserVO upDateUser(UserVO user);
	
	// 사용자 삭제
	public void deleteUser(String userId);
	 
	
}

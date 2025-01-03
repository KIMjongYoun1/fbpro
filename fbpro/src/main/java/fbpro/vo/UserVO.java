package fbpro.vo;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class UserVO {

	private String userId;
	private String fullName;
	private String password;
	private String email;
	private String roleCode;
	private LocalDateTime createdAt;
	
}

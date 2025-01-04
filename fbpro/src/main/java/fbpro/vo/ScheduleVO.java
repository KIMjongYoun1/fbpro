package fbpro.vo;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ScheduleVO {

	private String scheduleCode;
	private String userId;
	private String shiftCode;
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	private LocalDateTime createdAt;

}

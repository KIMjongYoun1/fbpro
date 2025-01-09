package fbpro.vo;

import lombok.Data;

@Data
public class ScheduleVO {

	private String scheduleCode;
	private String userId;
	private String shiftCode;
	private String startTime;
	private String endTime;
	private String createdAt;

}

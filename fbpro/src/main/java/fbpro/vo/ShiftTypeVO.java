package fbpro.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ShiftTypeVO {
	
	@JsonProperty("code") 
	private String shiftCode;
	private String startTime;
	private String endTime;
	private String description;
}

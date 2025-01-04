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

import fbpro.service.ScheduleService;
import fbpro.vo.ScheduleVO;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {

	@Autowired
	private ScheduleService scheduleService;
	
	// 전체 스케쥴 조회
	@GetMapping
	public List<ScheduleVO> getAllSchedule(){
		return scheduleService.getAllSchedules();
	}
	
	// 특정 사용자 조회
	@GetMapping("/{userId}")
	public List<ScheduleVO> getScheduleByUserId(@PathVariable String userId){
		return scheduleService.getScheduleByUserId(userId);
	}
	
	@PostMapping
	public String insertSchedule(@RequestBody ScheduleVO shcedule) {
		scheduleService.insertSchedule(shcedule);
		return "스케쥴이 추가되었습니다";
	}
	
	@PutMapping("/{scheduleCode}")
	public String updateSchedule(@PathVariable String scheduleCode, @RequestBody ScheduleVO schedule) {
		
		schedule.setShiftCode(scheduleCode);
		scheduleService.updateSchedule(schedule);
		
		return "스케쥴이 수정되었습니다.";
	}
	
	@DeleteMapping("/{scheduleCode}")
	public String deleteSchedule(@PathVariable String scheduleCode) {
		scheduleService.deleteSchedule(scheduleCode);
		return "스케쥴이 삭제되었습니다";
	}
	
	
}

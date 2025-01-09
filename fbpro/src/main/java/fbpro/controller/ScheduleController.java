package fbpro.controller;

import java.util.List;

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

import fbpro.service.ScheduleService;
import fbpro.vo.ScheduleVO;
@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/schedule")
public class ScheduleController {

	@Autowired
	private ScheduleService scheduleService;

	// 전체 스케쥴 조회
	@GetMapping("/code")
	public List<String> getAllSchedule() {
		return scheduleService.getAllSchedules();
	}

	// 특정 사용자 조회
	@GetMapping("/{userId}")
	public ResponseEntity<List<ScheduleVO>> getSchedulesWithDetails(@PathVariable String userId) {
		List<ScheduleVO> schedules = scheduleService.getSchedulesWithDetails(userId);
		return ResponseEntity.ok(schedules);
	}

	 @PostMapping("/add") // /api/schedule/add 경로로 요청을 받음
	    public ResponseEntity<String> insertSchedule(@RequestBody ScheduleVO schedule) {
		 	System.out.println("스케쥴이 추가 되었습니다");
	        try {
	            scheduleService.insertSchedule(schedule);
	            return ResponseEntity.ok("스케줄이 추가되었습니다.");
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("스케줄 배정 실패");
	        }
	    }

	@PutMapping("/{scheduleCode}")
	public String updateSchedule(@PathVariable String scheduleCode, @RequestBody ScheduleVO schedule) {
		System.out.println("코드추가");
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

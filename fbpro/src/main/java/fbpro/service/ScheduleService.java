package fbpro.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fbpro.dao.ScheduleDAO;
import fbpro.vo.ScheduleVO;

@Service
public class ScheduleService {

	@Autowired
	private ScheduleDAO scheduleDAO;

	public List<String> getAllSchedules() {
		return scheduleDAO.getAllSchedules();
	}

	public void insertSchedule(ScheduleVO schedule) {
		scheduleDAO.insertSchedule(schedule);
	}

	public void updateSchedule(ScheduleVO shcedule) {
		scheduleDAO.updateSchedule(shcedule);
	}

	public void deleteSchedule(String scheduleCode) {
		scheduleDAO.deleteSchedule(scheduleCode);
	}

	public List<ScheduleVO> getSchedulesWithDetails(String userId) {
		return scheduleDAO.getSchedulesWithDetails(userId);
	}

	public List<Map<String, Object>> getSchedulesForCalendar() {
	    List<Map<String, Object>> schedules = scheduleDAO.getSchedulesForCalendear();
	    List<Map<String, Object>> calendarSchedules = new ArrayList<>();

	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	    for (Map<String, Object> schedule : schedules) {
	        Map<String, Object> event = new HashMap<>();
	        try {
	            // createdAt 값 가져오기
	            String createdAt = schedule.get("createdAt").toString();

	            // LocalDate 변환 및 문자열 변환
	            LocalDate date = LocalDate.parse(createdAt, formatter);
	            String formattedDate = date.format(formatter);

	            // 이벤트 데이터 생성
	            event.put("userId", schedule.get("userId"));
	            event.put("shiftCode", schedule.get("shiftCode"));
	            event.put("start", formattedDate);

	            // Title 구성 (userId - shiftCode)
	            String title = schedule.get("userId") + " - " + schedule.get("shiftCode");
	            event.put("title", title);

	            calendarSchedules.add(event);
	        } catch (DateTimeParseException e) {
	            System.err.println("Invalid date format: " + schedule.get("createdAt"));
	            continue; // 잘못된 데이터는 건너뜀
	        }
	    }
	    return calendarSchedules;
	}



}

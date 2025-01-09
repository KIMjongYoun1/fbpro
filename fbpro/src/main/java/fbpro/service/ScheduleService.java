package fbpro.service;

import java.util.List;

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
}

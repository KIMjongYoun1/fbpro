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
	
	
	public List<ScheduleVO> getAllSchedules(){
		return scheduleDAO.getAllSchedules();
	}
	
	public List<ScheduleVO> getScheduleByUserId(String userId){
		return scheduleDAO.getSchedulesByUserId( userId);
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
	
}

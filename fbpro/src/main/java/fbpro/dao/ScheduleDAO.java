package fbpro.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import fbpro.vo.ScheduleVO;

@Mapper
public interface ScheduleDAO {
	
	// 배정 스케쥴 캘린더
	public List<Map<String, Object>> getSchedulesForCalendear();
	
	// 전체 스케쥴 조회
	public List<String> getAllSchedules();
	
	// 특정 스케쥴 조회
	public List<ScheduleVO> getSchedulesByUserId(String userId);
	
	// 스케쥴 추가
	public void insertSchedule(ScheduleVO schedule);
	
	// 스케쥴 수정
	public void updateSchedule(ScheduleVO schedule);
	
	// 스케쥴 삭제
	public void deleteSchedule(String scheduleCode, String userId);
	
	//스케쥴조회
	public List<ScheduleVO> getSchedulesWithDetails(String userId);
	
	// 스케쥴 상세
	public ScheduleVO getScheduleByCode(String scheduleCode);
	
}

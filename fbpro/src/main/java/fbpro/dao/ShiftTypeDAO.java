package fbpro.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import fbpro.vo.ShiftTypeVO;

@Mapper
public interface ShiftTypeDAO {
	
	//스케쥴코드,유형 추가
	public void insertShiftType(ShiftTypeVO shiftType);
	
	public List<ShiftTypeVO> getAllShiftType();
	
	public List<ShiftTypeVO> findAllShiftType();
	
}

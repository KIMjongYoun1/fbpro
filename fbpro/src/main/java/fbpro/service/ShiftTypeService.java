package fbpro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fbpro.dao.ShiftTypeDAO;
import fbpro.vo.ShiftTypeVO;

@Service
public class ShiftTypeService {
	
	@Autowired
	private ShiftTypeDAO shiftTypeDAO;
	
	public void createShift(ShiftTypeVO shiftType) {
		shiftTypeDAO.insertShiftType(shiftType);
	}
	
	public List<ShiftTypeVO> getAllShiftType() {
		return shiftTypeDAO.getAllShiftType();
	}
}

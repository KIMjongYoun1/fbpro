package fbpro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fbpro.service.ShiftTypeService;
import fbpro.vo.ShiftTypeVO;

@RestController
@RequestMapping("/api/shift-types")
public class ShiftController {

	@Autowired
	private ShiftTypeService shiftTypeService;

	@GetMapping
	public ResponseEntity<List<ShiftTypeVO>> getAllShiftType() {
		List<ShiftTypeVO> shiftTypes = shiftTypeService.getAllShiftType();
		return ResponseEntity.ok(shiftTypes);
	}

	@PostMapping
	public ResponseEntity<String> addShiftType(@RequestBody ShiftTypeVO shiftType) {
		System.out.println("Received ShiftType: " + shiftType.getShiftCode()); // 제대로 받은 값 확인
		   System.out.println("Received Shift Code: " + shiftType.getShiftCode());
		    System.out.println("Received Description: " + shiftType.getDescription());
		    System.out.println("Received Start Time: " + shiftType.getStartTime());
		    System.out.println("Received End Time: " + shiftType.getEndTime());
		try {
			shiftTypeService.createShift(shiftType);
			return ResponseEntity.ok("스케쥴 코드가 추가되었습니다.");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("스케쥴 코드 추가 실패");
		}
	}

}

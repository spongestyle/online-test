package goodee.gdj58.online.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import goodee.gdj58.online.service.TeacherService;

@RestController
public class TeacherRestController {
	@Autowired TeacherService teacherService;
	
	@GetMapping("/teacher/idck")
	public String idck(@RequestParam(value ="teacherId") String teacherId) {
		return teacherService.getTeacherId(teacherId);
	}
}

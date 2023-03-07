package goodee.gdj58.online.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import goodee.gdj58.online.service.IdService;
import goodee.gdj58.online.service.TeacherService;
import goodee.gdj58.online.vo.Teacher;
import lombok.extern.slf4j.Slf4j;

@Slf4j // static Log log = new Log() 로그 객체를 선언하여 사용할 수 있게 함
@Controller
public class TeacherController {
	@Autowired TeacherService teacherService;
	@Autowired IdService idService;
	
	

	// 강사 리스트 teacher ===> 
	
	

	
	
	
	// 강사 로그인
	@GetMapping("/teacher/loginTeacher")
	public String loginTeacehr() {
		return "teacher/loginTeacher";
	}
	
	@PostMapping("/loginTeacher")
	public String loginTeacehr(HttpSession session, Teacher teacher) {
		Teacher resultTeacher = teacherService.login(teacher);	// row == 1이면 입력성공
		session.setAttribute("loginTeacher", resultTeacher);
		return "redirect:/teacher/testList";
	}
	// 로그아웃
	@GetMapping("/teacher/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/home";
	}
	
		
	// 강사 비밀번호 수정
	@GetMapping("/teacher/modifyTeacherPw")
	public String modifyTeacherPw(HttpSession session) {
		Teacher loginTeacher = (Teacher) session.getAttribute("loginTeacher");
		if(loginTeacher == null) {
			return "redirect:/teacher/loginTeacher";
		}
		
		return "teacher/modifyTeacher";
	}
	
	// 강사 삭제
	@GetMapping("/teacher/removeTeacher")
	public String removeTeacher(@RequestParam("teacherNo") int teacherNo) {	
		teacherService.removeTeacher(teacherNo);	// row == 1이면 삭제성공
		return "redirect:/teacher/teacherList";
	}
		
		
	// 강사 추가
	@GetMapping("/teacher/addTeacher")
	public String addTeacher() {	
		return "teacher/addTeacher";
	}
	@PostMapping("/teacher/addTeacher")
	public String addTeacher(Model model, Teacher teacher) {		
		String id = idService.getIdCheck(teacher.getTeacherId());
		if(id != null) { // 입력한 ID가 기존 DB에 존재 시, 다시 회원가입 페이지로 이동
			model.addAttribute("errorMsg", "중복된 ID");
			return "teacher/addTeacher";
		}
		
		int row = teacherService.addTeacher(teacher);
		if(row != 1) { // row != 1이면 입력실패
			model.addAttribute("errorMsg", "시스템 에러로 인한 실패");
			return "teacher/addTeacher";
		}
		return "redirect:/teacher/teacherList"; // sendRedict , cm
	}
	
	
	
	// 강사 리스트 employee ==> teacher
	@GetMapping("/teacher/teacherList")
	public String teacherList(Model model
			, @RequestParam(value="currentPage", defaultValue="1") int currentPage
			, @RequestParam(value="rowPerPage", defaultValue="10") int rowPerPage
			, @RequestParam(value="searchWord", defaultValue="") String searchWord) {
			// int currentPage = request.getParameter("currentPage");
		
		List<Teacher> list = teacherService.getTeacherList(currentPage,rowPerPage,searchWord);
		
		model.addAttribute("list", list); // request.setAttribute("list", list) 기능 (매개변수 model 필요)
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("rowPerPage", rowPerPage);
		model.addAttribute("searchWord", searchWord);
		
		Map<String, Object> map = teacherService.getTeacherCount(currentPage, rowPerPage, searchWord);
		log.debug("\u001B[31m" + map.get("startPage") + "<-- startPage");
		log.debug("\u001B[31m" + map.get("endPage") + "<-- endPage");
		log.debug("\u001B[31m" + map.get("lastPage") + "<-- lastPage");
		model.addAttribute("map", map);
		
		return "teacher/teacherList";
	}

	
}

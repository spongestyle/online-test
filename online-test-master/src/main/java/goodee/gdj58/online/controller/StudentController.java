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
import goodee.gdj58.online.service.StudentService;
import goodee.gdj58.online.vo.Student;
import lombok.extern.slf4j.Slf4j;

@Slf4j // static Log log = new Log() 로그 객체를 선언하여 사용할 수 있게 함
@Controller
public class StudentController {
	@Autowired StudentService studentService;
	@Autowired IdService idService;
	



	// 학생 로그인
	@GetMapping("/student/loginStudent")
	public String loginStudent () {
		return "student/loginStudent";
	}
	@PostMapping ("/loginStudent")
	public String loginStudent(HttpSession session, Student student) {
		Student resultStudent = studentService.login(student);
		session.setAttribute("loginStudent", resultStudent);
		
		return "redirect:/student/studentList";
	}
	// 로그아웃
	@GetMapping("/student/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/home";
	}
	

	
	
	
	// 학생 비밀번호 수정
	@GetMapping("student/modifyStudentPw")
	public String modifyStudentPw(HttpSession session) {
		Student loginStudent =(Student) session.getAttribute("loginStudent");
		if(loginStudent == null) {
			return "redirect:/student/loginStudent";
		}
		
		return "student/modifyStudent";

	}
	
	// 학생 삭제
	@GetMapping("student/removeStudent")
	public String removeStudent(@RequestParam("studentNo") int studentNo) {	
		studentService.removeStudent(studentNo);	// row == 1이면 삭제성공
		return "redirect:/student/studentList";
	}
	
	
	// 학생 추가
	@GetMapping ("/student/addStudent")
	public String addStudent() {
		return "student/addStudent";
	}
	@PostMapping("/student/addStudent")
	public String addStudent(Model model, Student student) {
		String id = idService.getIdCheck(student.getStudentId());
		if (id != null) {
			model.addAttribute("errorMsg", "중복된 ID");
			return "student/addStudent";
		}
		
		int row = studentService.addStudent(student);
		if( row != 1) {
			model.addAttribute("errorMsg", "시스템 에러로 인한 실패");
			return "student/addStudent";
			
		}
		return "redirect:/student/studentList";
	}
	
	
	// 학생 리스트
	@GetMapping("/student/studentList")
	public String studentList(Model model
			, @RequestParam(value="currentPage", defaultValue="1") int currentPage
			, @RequestParam(value="rowPerPage", defaultValue="10") int rowPerPage
			, @RequestParam(value="searchWord", defaultValue="") String searchWord) {
			// int currentPage = request.getParameter("currentPage");
		
		List<Student> list = studentService.getStudentList(currentPage, rowPerPage, searchWord);
		
		model.addAttribute("list", list); // request.setAttribute("list", list) 기능 (매개변수 model 필요)
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("rowPerPage", rowPerPage);
		model.addAttribute("searchWord", searchWord);
		
		Map<String, Object> map = studentService.getStudentCount(currentPage, rowPerPage, searchWord);
		log.debug("\u001B[31m" + map.get("startPage") + "<-- startPage");
		log.debug("\u001B[31m" + map.get("endPage") + "<-- endPage");
		log.debug("\u001B[31m" + map.get("lastPage") + "<-- lastPage");
		model.addAttribute("map", map);
		
		return "student/studentList";
		
	}
}
	
	
	
	

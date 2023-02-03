package goodee.gdj58.online.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import goodee.gdj58.online.service.EmployeeService;
import goodee.gdj58.online.service.IdService;
import goodee.gdj58.online.service.StudentService;
import goodee.gdj58.online.service.TeacherService;
import goodee.gdj58.online.vo.Employee;
import goodee.gdj58.online.vo.Student;
import goodee.gdj58.online.vo.Teacher;

@Controller
public class EmployeeController {
	@Autowired EmployeeService employeeService;
	@Autowired StudentService studentService;
	@Autowired TeacherService teacherService;
	@Autowired IdService idService;
	
	// 비밀번호 수정
	@GetMapping("employee/modifyEmpPw")
	public String modifyEmpPw(HttpSession session) {
		
		Employee loginEmp = (Employee) session.getAttribute("loginEmp");	
		if(loginEmp == null) { // 로그인 되어있지 않다면 로그인페이지로 이동
			return "redirect:/employee/loginEmp";
		}
		
		return "employee/modifyEmpPw";	
	}
	
	@PostMapping("employee/modifyEmpPw")
	public String modifyEmpPw(HttpSession session
			, @RequestParam(value="oldPw") String oldPw
			, @RequestParam(value="newPw") String newPw) {
		
		
		Employee loginEmp = (Employee) session.getAttribute("loginEmp");	
		if(loginEmp == null) { // 로그인 되어있지 않다면 로그인페이지로 이동
			return "redirect:/employee/loginEmp";
		}

		employeeService.updateEmployeePw(loginEmp.getEmpNo(), oldPw, newPw);
		
		return "redirect:/employee/empList";
		
	}
	// 로그아웃
	@GetMapping("/employee/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/employee/loginEmp";
	}
		
	// 로그인
	@GetMapping("/employee/loginEmp")
	public String loginEmp(HttpSession session) {
		Employee loginEmp = (Employee) session.getAttribute("loginEmp");
		
		if(loginEmp != null) { // 이미 로그인 중이라면 redirect:/employee/empList
			return "redirect:/employee/empList";
		}
		
		return "employee/loginEmp";
	}
	
	@PostMapping("/employee/loginEmp")
	public String loginEmp(HttpSession session, Employee employee) {
		Employee resultEmp = employeeService.login(employee);	// row == 1이면 입력성공
		if(resultEmp == null) { // 로그인 실패
			return "employee/loginEmp";
		}
		
		session.setAttribute("loginEmp", resultEmp);
		return "redirect:/employee/empList";
	}
		
	// employee 삭제
	@GetMapping("/employee/removeEmp")
	public String removeEmp(HttpSession session, @RequestParam("empNo") int empNo) {
		
		Employee loginEmp = (Employee) session.getAttribute("loginEmp");	
		if(loginEmp == null) { // 로그인 되어있지 않다면 로그인페이지로 이동
			return "redirect:/employee/loginEmp";
		}
		
		employeeService.removeEmp(empNo);	// row == 1이면 삭제성공
		return "redirect:/employee/empList";
	}
	
	// student 삭제
	@GetMapping("/student/removeStudent")
	public String removeStudent(HttpSession session, @RequestParam("studentNo") int studentNo) {
		
		Employee loginEmp = (Employee) session.getAttribute("loginEmp");	
		if(loginEmp == null) { // 로그인 되어있지 않다면 로그인페이지로 이동
			return "redirect:/employee/loginEmp";
		}
		
		studentService.removeStudent(studentNo);	// row == 1이면 삭제성공
		return "redirect:/student/studentList";
	}
		
	// teacher 삭제
	@GetMapping("/teacher/removeTeacher")
	public String removeTeacher(HttpSession session, @RequestParam("teacherNo") int teacherNo) {
		
		Employee loginEmp = (Employee) session.getAttribute("loginEmp");	
		if(loginEmp == null) { // 로그인 되어있지 않다면 로그인페이지로 이동
			return "redirect:/employee/loginEmp";
		}
		
		teacherService.removeTeacher(teacherNo);	// row == 1이면 삭제성공
		return "redirect:/teacher/teacherList";
	}
	
	// employee 입력
	@GetMapping("/employee/addEmp")
	public String addEmp(HttpSession session) {
		
		Employee loginEmp = (Employee) session.getAttribute("loginEmp");	
		if(loginEmp == null) { // 로그인 되어있지 않다면 로그인페이지로 이동
			return "redirect:/employee/loginEmp";
		}
		
		return "employee/addEmp";
	}
	@PostMapping("/employee/addEmp")
	public String addEmp(HttpSession session, Model model, Employee employee) {
		
		Employee loginEmp = (Employee) session.getAttribute("loginEmp");	
		if(loginEmp == null) { // 로그인 되어있지 않다면 로그인페이지로 이동
			return "redirect:/employee/loginEmp";
		}
		
		String id = idService.getIdCheck(employee.getEmpId());
		if(id != null) { // 입력한 ID가 기존 DB에 존재 시, 다시 회원가입 페이지로 이동
			model.addAttribute("errorMsg", "중복된 ID");
			return "employee/addEmp";
		}
		
		int row = employeeService.addEmp(employee);
		if(row != 1) { // row != 1이면 입력실패
			model.addAttribute("errorMsg", "시스템 에러로 인한 실패");
			return "employee/addEmp";
		}
		return "redirect:/employee/empList"; // sendRedict , cm
	}
	
	// student 입력
	@GetMapping("/student/addStudent")
	public String addStudent(HttpSession session) {
		
		Employee loginEmp = (Employee) session.getAttribute("loginEmp");	
		if(loginEmp == null) { // 로그인 되어있지 않다면 로그인페이지로 이동
			return "redirect:/employee/loginEmp";
		}
		
		return "student/addStudent";
	}
	@PostMapping("/student/addStudent")
	public String addStudent(HttpSession session, Model model, Student student) {
		
		Employee loginEmp = (Employee) session.getAttribute("loginEmp");	
		if(loginEmp == null) { // 로그인 되어있지 않다면 로그인페이지로 이동
			return "redirect:/employee/loginEmp";
		}
		
		String id = idService.getIdCheck(student.getStudentId());
		if(id != null) { // 입력한 ID가 기존 DB에 존재 시, 다시 회원가입 페이지로 이동
			model.addAttribute("errorMsg", "중복된 ID");
			return "student/addStudent";
		}
		
		int row = studentService.addStudent(student);
		if(row != 1) { // row != 1이면 입력실패
			model.addAttribute("errorMsg", "시스템 에러로 인한 실패");
			return "student/addStudent";
		}
		return "redirect:/student/studentList"; // sendRedict , cm
	}
	
	// teacher 입력
	@GetMapping("/teacher/addTeacher")
	public String addTeacher(HttpSession session) {
		
		Employee loginEmp = (Employee) session.getAttribute("loginEmp");	
		if(loginEmp == null) { // 로그인 되어있지 않다면 로그인페이지로 이동
			return "redirect:/employee/loginEmp";
		}
		
		return "teacher/addTeacher";
	}
	@PostMapping("/teacher/addTeacher")
	public String addTeacher(HttpSession session, Model model, Teacher teacher) {
		
		Employee loginEmp = (Employee) session.getAttribute("loginEmp");	
		if(loginEmp == null) { // 로그인 되어있지 않다면 로그인페이지로 이동
			return "redirect:/employee/loginEmp";
		}
		
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
	
	// EmpList 출력
	@GetMapping("/employee/empList")
	public String empList(HttpSession session, Model model
			, @RequestParam(value="currentPage", defaultValue="1") int currentPage
			, @RequestParam(value="rowPerPage", defaultValue="10") int rowPerPage) {
			// int currentPage = request.getParameter("currentPage");
		
		Employee loginEmp = (Employee) session.getAttribute("loginEmp");	
		if(loginEmp == null) { // 로그인 되어있지 않다면 로그인페이지로 이동
			return "redirect:/employee/loginEmp";
		} 
		
		List<Employee> list = employeeService.getEmployeeList(currentPage,rowPerPage);
		
		model.addAttribute("list", list); // request.setAttribute("list", list) 기능 (매개변수 model 필요)
		model.addAttribute("currentPage", currentPage);
		return "employee/empList";
	}
	
	// StudentList 출력
	@GetMapping("/student/studentList")
	public String studentList(HttpSession session, Model model
			, @RequestParam(value="currentPage", defaultValue="1") int currentPage
			, @RequestParam(value="rowPerPage", defaultValue="10") int rowPerPage) {
			// int currentPage = request.getParameter("currentPage");
		
		Employee loginEmp = (Employee) session.getAttribute("loginEmp");	
		if(loginEmp == null) { // 로그인 되어있지 않다면 로그인페이지로 이동
			return "redirect:/employee/loginEmp";
		} 
		
		List<Student> list = studentService.getStudentList(currentPage,rowPerPage);
		
		model.addAttribute("list", list); // request.setAttribute("list", list) 기능 (매개변수 model 필요)
		model.addAttribute("currentPage", currentPage);
		return "student/studentList";
	}
	
	// TeacherList 출력
	@GetMapping("/teacher/teacherList")
	public String teacherList(HttpSession session, Model model
			, @RequestParam(value="currentPage", defaultValue="1") int currentPage
			, @RequestParam(value="rowPerPage", defaultValue="10") int rowPerPage) {
			// int currentPage = request.getParameter("currentPage");
		
		Employee loginEmp = (Employee) session.getAttribute("loginEmp");	
		if(loginEmp == null) { // 로그인 되어있지 않다면 로그인페이지로 이동
			return "redirect:/employee/loginEmp";
		} 
		
		List<Teacher> list = teacherService.getTeacherList(currentPage,rowPerPage);
		
		model.addAttribute("list", list); // request.setAttribute("list", list) 기능 (매개변수 model 필요)
		model.addAttribute("currentPage", currentPage);
		return "teacher/teacherList";
	}
}

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

import goodee.gdj58.online.service.TestService;
import goodee.gdj58.online.vo.Example;
import goodee.gdj58.online.vo.Question;
import goodee.gdj58.online.vo.Student;
import goodee.gdj58.online.vo.Teacher;
import goodee.gdj58.online.vo.Test;
import lombok.extern.slf4j.Slf4j;

@Slf4j // static Log log = new Log() 로그 객체를 선언하여 사용할 수 있게 함
@Controller
public class TestController {
	@Autowired private TestService testService;
	
	
	// 시험 등록
	@PostMapping("/teacher/addQuestion")
	public String addQuestion(Question question
								, @RequestParam(value = "exampleTitle") String[] exampleTitle
								, @RequestParam(value = "exampleIdx") int[] exampleIdx
								, @RequestParam(value = "exampleOx") int exampleOx) {
		int addQuestion = testService.addQuestion(question);
		log.debug("\u001B[31m" + question.getQuestionNo() + "	<=questionNo");
		
		if(addQuestion == 1) {
			log.debug("\u001B[31m" + "문제 등록성공");
		}
		Example[] example = new Example[4]; // 보기 4개
		for(int i=0; i<example.length; i++) {
			example[i] = new Example();
			example[i].setQuestionNo(question.getQuestionNo());
			example[i].setExampleTitle(exampleTitle[i]);
			example[i].setExampleIdx(exampleIdx[i]);
			example[i].setExampleOx("오답");
			if(exampleOx == (i+1)) {
				example[i].setExampleOx("정답");
			} 
			int addExample = testService.addExample(example[i]);
			if(addExample == 1) {
				log.debug("\u001B[31m" + (i+1) + "보기 등록 성공");
			}
		}
		return "redirect:/teacher/testOne?testNo="+question.getTestNo(); 
	}
	

	
	// 시험 상세보기
	@GetMapping("/teacher/testOne")
	public String getTestOne(Model model
							, @RequestParam(value = "testNo") int testNo) {
		List<Map<String, Object>> list = testService.getTestOne(testNo);
		Test test = testService.getTestTitle(testNo); // 테스트 정보
		List<Map<String, Object>> ox = testService.getTestOx(testNo);
		model.addAttribute("test",test);
		model.addAttribute("list",list);
		model.addAttribute("ox", ox);
		log.debug("\u001B[31m" + list.size()/4 + "	<= 문제 개수");
		int questionCount = list.size()/4;
		model.addAttribute("questionCount", questionCount);
		return "teacher/testOne";
	}
		
	
	
	// 시험 삭제(문제, 응시자 없을때만 가능)
	@GetMapping("/teacher/removeTest")
	public String removeTest(@RequestParam(value = "testNo") int testNo) {
		int row = testService.removeTest(testNo);
		if(row == 1) {
			log.debug("\u001B[31m"+"시험 삭제 성공");
		}
		log.debug("\u001B[31m" + testNo + "	<= testNo");
		return "redirect:/teacher/testList";
	}
	
	
	
	// test 시험 회차 추가
	@PostMapping("/teacher/addTest")
	public String addTest(Test test) {
		int row = testService.addTest(test);
		if( row == 0) {
			log.debug("\u001B[32m"+"시험 추가 실패");
		} else {
			log.debug("\u001B[32m"+"시험 추가 성공");
		}
		return "redirect:/teacher/testList";
	}


	
	// 시험 리스트 출력 (선생본인만)
	@GetMapping("/teacher/testList")
	public String testListForTeacher(Model model, HttpSession session
			, @RequestParam(value="teacherNo", defaultValue="0") int teacherNo
			, @RequestParam(value="currentPage", defaultValue="1") int currentPage
			, @RequestParam(value="rowPerPage", defaultValue="10") int rowPerPage
			, @RequestParam(value="searchWord", defaultValue="") String searchWord) {
		
		if(session.getAttribute("loginTeacher") != null) { // teacher 로그인 시, 자기가 등록한 문제만 출력하기 위함
			Teacher loginTeacher = (Teacher) session.getAttribute("loginTeacher");
			teacherNo = loginTeacher.getTeacherNo();
		}	
		
		List<Map<String, Object>> list = testService.getTestList(teacherNo, currentPage, rowPerPage, searchWord);
		
		model.addAttribute("list", list); // request.setAttribute("list", list) 기능 (매개변수 model 필요)
		model.addAttribute("teacherNo", teacherNo);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("rowPerPage", rowPerPage);
		model.addAttribute("searchWord", searchWord);
		
		Map<String, Object> map = testService.getTestCount(teacherNo, currentPage, rowPerPage, searchWord);
		model.addAttribute("map", map);
		
		return "test/testList";		
	}
	
	// 시험 리스트 출력(학생 전체 리스트가 보이게)
	@GetMapping("/student/testList")
	public String testListForStudent(Model model, HttpSession session
			, @RequestParam(value="teacherNo", defaultValue="0") int teacherNo
			, @RequestParam(value="studentNo", defaultValue="0") int studentNo
			, @RequestParam(value="currentPage", defaultValue="1") int currentPage
			, @RequestParam(value="rowPerPage", defaultValue="10") int rowPerPage
			, @RequestParam(value="searchWord", defaultValue="") String searchWord) {	
		
	
		
		List<Map<String, Object>> list = testService.getTestList(teacherNo, currentPage, rowPerPage, searchWord);
		
		model.addAttribute("list", list); // request.setAttribute("list", list) 기능 (매개변수 model 필요)
		model.addAttribute("teacherNo", teacherNo);
		model.addAttribute("studentNo", studentNo);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("rowPerPage", rowPerPage);
		model.addAttribute("searchWord", searchWord);
		
		Map<String, Object> map = testService.getTestCount(teacherNo, currentPage, rowPerPage, searchWord);
		model.addAttribute("map", map);
		
		return "test/testList";
	}
}

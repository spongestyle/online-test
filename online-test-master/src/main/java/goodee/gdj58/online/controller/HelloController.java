package goodee.gdj58.online.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {
	
	@GetMapping("/test") 
	public String test() {
		System.out.println("test.....");
		return "test";
	}
	
	
	@GetMapping("/test2") 
	public void test2() {
		System.out.println("test.....");
	}
	// return 타입이 없으면 메소드가 view이름이 된다.
	
	
	@GetMapping("/test3") 
	public String test3() {
		System.out.println("test.....");
		return "test";
	}
	// get, post 를 둘다 사용해야한다면
	// @RequestMapping(value="/")
	
	
	@GetMapping("/test4")
	public ModelAndView test4() {
		System.out.println("ModelAndView test....");
		ModelAndView mv = new ModelAndView();
		mv.setViewName("test");
		return mv;
	}
	// ModelAndView객체를 생성하여 mv의 뷰이름을 test로 설정하여 리턴
}

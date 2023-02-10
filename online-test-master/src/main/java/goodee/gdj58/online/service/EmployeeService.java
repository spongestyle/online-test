package goodee.gdj58.online.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import goodee.gdj58.online.mapper.EmployeeMapper;
import goodee.gdj58.online.mapper.IdMapper;
import goodee.gdj58.online.vo.Employee;
import lombok.extern.slf4j.Slf4j;

@Slf4j // static Log log = new Log() 로그 객체를 선언하여 사용할 수 있게 함
@Service
@Transactional
public class EmployeeService {
	@Autowired private EmployeeMapper employeeMapper;	// DI = new EmployeeMapper()
	
	
	// 회원가입중복검사
		public String getEmpId(String empId) {
			// 사용가능한 id면 yes 아니면 no
			String resultStr = "NO";
			

			if(employeeMapper.selectEmpId(empId) == null) {
				resultStr = "YES";
			}

			return resultStr;
	}
		
		
	// emp 비밀번호 변경
	public int updateEmployeePw(int empNo, String oldPw, String newPw) {
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("empNo", empNo);
		paramMap.put("oldPw", oldPw);
		paramMap.put("newPw", newPw);
		
		return employeeMapper.updateEmployeePw(paramMap);
	}
	
	
	// emp 로그인
	public Employee login(Employee emp) {
		return employeeMapper.login(emp);
	}
	
	// emp 삭제
	public int removeEmp(int empNo) {
		return employeeMapper.deleteEmployee(empNo);
	}
	
	// emp 등록
	public int addEmp(Employee employee) {
		return employeeMapper.insertEmployee(employee);
	}
	
	
	// emp 리스트
	public List<Employee> getEmployeeList(int currentPage, int rowPerPage, String searchWord) {
		
		int beginRow = (currentPage-1) * rowPerPage;
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("beginRow", beginRow);
		paramMap.put("rowPerPage", rowPerPage);
		paramMap.put("searchWord", searchWord);
		
		return employeeMapper.selectEmployeeList(paramMap);
	}
	
	// emp 리스트 count
	public Map<String, Object> getEmployeecount(int currentPage, int rowPerPage, String searchWord) {
		int employeeCount = employeeMapper.selectEmployeeCount(searchWord);
		log.debug("\u001B[31m" + employeeCount + "<-- employeeCount");
		

		int startPage = currentPage / 10 * 10 + 1;
		int endPage = currentPage / 10 * 10 + 10;
		if(currentPage % 10 == 0) { // 페이지 일의 자리 숫자가 0일경우
			startPage = startPage - 10;			
			endPage = endPage - 10;
		}
		
		int lastPage = employeeCount / rowPerPage;
		if(lastPage % rowPerPage != 0  || lastPage == 0) {
			lastPage++;	
		}
		
		if(endPage > lastPage) {
			endPage = lastPage;
		}
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("startPage", startPage);
		resultMap.put("endPage", endPage);
		resultMap.put("lastPage", lastPage);
		
		return resultMap;
		
	}
}
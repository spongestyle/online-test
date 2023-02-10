package goodee.gdj58.online.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import goodee.gdj58.online.vo.Employee;

@Mapper
public interface EmployeeMapper {
	
	
	// 사용가능한 아이디면 null, 불가능하면 return 
	public String selectEmpId(String empId); 
	
	
	// emp 비밀번호 변경
	int updateEmployeePw(Map<String, Object> paramMap);
	
	// emp 로그인
	Employee login(Employee employee);
	
	// emp 탈퇴
	int deleteEmployee(int empNo);
	
	// emp 가입
	int insertEmployee(Employee employee);
	
	// emp 리스트
	List<Employee> selectEmployeeList(Map<String, Object> paramMap);

	// emp 리스트 cnt
	int selectEmployeeCount(String searchWord);
}

// @Mapper가 하는 역할
// public class EmployeeMapperClass implements EmployeeMapper {} 클래스 선언 및 객체 생성
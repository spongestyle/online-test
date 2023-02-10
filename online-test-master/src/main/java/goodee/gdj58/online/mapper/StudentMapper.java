package goodee.gdj58.online.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import goodee.gdj58.online.vo.Student;

@Mapper
public interface StudentMapper {
	
	// 사용가능한 아이디면 null, 불가능하면 return 
	public String selectStudentId(String studentId); 
	
	// 학생 로그인
	Student login(Student student);
	
	// employee 에서 접근
	// 학생 탈퇴
	int deleteStudent(int studentNo);
	
	// 학생 가입
	int insertStudent(Student student);
	
	// 학생 리스트 조회
	List<Student> selectStudentList(Map<String, Object> paramMap);

	// 총 학생 리스트 조회 cnt
	int selectStudentCount(String searchWord);
}

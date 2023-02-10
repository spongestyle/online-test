package goodee.gdj58.online.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import goodee.gdj58.online.vo.Teacher;

@Mapper
public interface TeacherMapper {
	
	// 사용가능한 아이디면 null, 불가능하면 return 
	public String selectTeacherId(String teacherId); 
	
	// teacher 에서 접근함
	// 강사 로그인
	Teacher login(Teacher teacher);
	
	// employee 에서 접근함
	// 강사 탈퇴
	int deleteTeacher(int teacherNo);
	
	// 강사 가입
	int insertTeacher(Teacher teacher);
	
	// 강사 리스트조회
	List<Teacher> selectTeacherList(Map<String, Object> paramMap);

	// 총 강사리스트 조회 cnt
	int selectTeacherCount(String searchWord);
}

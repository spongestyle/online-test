package goodee.gdj58.online.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import goodee.gdj58.online.mapper.TeacherMapper;
import goodee.gdj58.online.vo.Teacher;
import lombok.extern.slf4j.Slf4j;

@Slf4j // static Log log = new Log() 로그 객체를 선언하여 사용할 수 있게 함
@Service
@Transactional
public class TeacherService {
	@Autowired
	private TeacherMapper teacherMapper;
	
	// 강사 아이디 중복
	public String getTeacherId(String teacherId) {
		// 사용가능한 id면 yes 아니면 no
		String resultStr = "NO";
		

		if(teacherMapper.selectTeacherId(teacherId) == null) {
			resultStr = "YES";
		}

		return resultStr;
	}

	
	
	
	
	// 강사 로그인
	public Teacher login(Teacher teacher) {
		return teacherMapper.login(teacher);
	}
	
	// 강사 삭제
	public int removeTeacher(int teacherNo) {
		return teacherMapper.deleteTeacher(teacherNo);
	}
	
	// 강사 추가
	public int addTeacher(Teacher teacher) {
		return teacherMapper.insertTeacher(teacher);
	}
	
	
	// 강사 리스트 (검색)
	public List<Teacher> getTeacherList(int currentPage, int rowPerPage, String searchWord) {
		int beginRow = (currentPage-1) * rowPerPage;
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("beginRow", beginRow);
		paramMap.put("rowPerPage", rowPerPage);
		paramMap.put("searchWord", searchWord);
		
		return teacherMapper.selectTeacherList(paramMap);
	}
	
	
	// 총 강사 리스트 count
	public Map<String, Object> getTeacherCount(int currentPage, int rowPerPage, String searchWord) {
		int teacherCount = teacherMapper.selectTeacherCount(searchWord);
		log.debug("\u001B[31m" + teacherCount + "<-- teacherCount");
		
		int startPage = currentPage / 10 * 10 + 1;
		int endPage = currentPage / 10 * 10 + 10;
		if(currentPage % 10 == 0) { // 페이지 일의 자리 숫자가 0일경우
			startPage = startPage - 10;			
			endPage = endPage - 10;
		}
		
		int lastPage = teacherCount / rowPerPage;
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

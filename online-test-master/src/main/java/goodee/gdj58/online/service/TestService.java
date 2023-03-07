package goodee.gdj58.online.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import goodee.gdj58.online.mapper.ExampleMapper;
import goodee.gdj58.online.mapper.QuestionMapper;
import goodee.gdj58.online.mapper.TestMapper;
import goodee.gdj58.online.vo.Example;
import goodee.gdj58.online.vo.Question;
import goodee.gdj58.online.vo.Test;
import lombok.extern.slf4j.Slf4j;

@Slf4j // static Log log = new Log() 로그 객체를 선언하여 사용할 수 있게 함
@Service
@Transactional
public class TestService {
	
	@Autowired private TestMapper testMapper;
	@Autowired private QuestionMapper questionMapper;
	@Autowired private ExampleMapper exampleMapper;

	
	// 문제 등록
	public int addQuestion(Question question) {
		return testMapper.insertQuestion(question);
	}

	// 보기 등록
	public int addExample(Example example) {
		return testMapper.insertExample(example);
	}
	
	
	
	
	
	// 시험 상세보기
	public Test getTestTitle(int testNo) {
		return testMapper.selectTestTitle(testNo);
	}
	public List<Map<String, Object>> getTestOne(int testNo) {
		return testMapper.selectTestOne(testNo);
	}
	public List<Map<String, Object>> getTestOx(int testNo) {
		return testMapper.selectTestOx(testNo);
	}
	
	
	
	
	// 시험 삭제
	public int removeTest(int testNo) {
		return testMapper.deleteTest(testNo);
	}
	


	
	// test 시험회차 추가하기
	public int addTest (Test test)  {
		return testMapper.insertTest(test);
	}
	

	
	// 총 test 리스트 count
	public Map<String, Object> getTestCount (int teacherNo, int currentPage, int rowPerPage, String searchWord) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("teacherNo", teacherNo);
		paramMap.put("searchWord", searchWord);
		
		int testCount = testMapper.selectTestCount(paramMap);
		
		// 페이징
		int startPage = currentPage / 10 * 10 + 1;
		int endPage = currentPage / 10 * 10 + 10;
		if(currentPage % 10 == 0) { // 페이지 일의 자리 숫자가 0일경우
			startPage = startPage - 10;			
			endPage = endPage - 10;
		}
		
		int lastPage = testCount / rowPerPage;
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

	
	// 시험 회차 리스트 (선생이름, 시험타이틀, 응시날짜)
	public List<Map<String, Object>> getTestList (int teacherNo, int currentPage, int rowPerPage, String searchWord) {
		int beginRow = (currentPage-1) * rowPerPage;
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("teacherNo", teacherNo);
		paramMap.put("beginRow", beginRow);
		paramMap.put("rowPerPage", rowPerPage);
		paramMap.put("searchWord", searchWord);
		
		
		return testMapper.selectTestList(paramMap);
	}

}

package goodee.gdj58.online.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import goodee.gdj58.online.vo.Employee;
import goodee.gdj58.online.vo.Example;
import goodee.gdj58.online.vo.Question;
import goodee.gdj58.online.vo.Test;

@Mapper
public interface TestMapper {
	
	// test 질문, 보기 추가하기
	int insertQuestion(Question question);
	int insertExample(Example example);
	
	// test one 하나의 시험 출력
	List<Map<String, Object>> selectTestOne(int testNo);
	List<Map<String, Object>> selectTestOx(int testNo);
	Test selectTestTitle(int testNo);
	
	
	// test 시험 회차 삭제하기
	int deleteTest(int testNo);
	
	// test 시험 회차 추가하기
	int insertTest(Test test);
	
	
	// test 리스트 count 
	int selectTestCount(Map<String, Object> paramMap);
	
	
	// test 리스트 조회 (teachName + testTitle)
	List<Map<String, Object>> selectTestList(Map<String, Object> paramMap);
	
	

}

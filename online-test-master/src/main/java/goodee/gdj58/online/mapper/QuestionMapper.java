package goodee.gdj58.online.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import goodee.gdj58.online.vo.Question;

@Mapper
public interface QuestionMapper {
	
	// 질문 수정
	int updateQuestion(Question question);
	
	// 질문 삭제
	int deleteQuestion(int questionNo);
	
	// 질문 추가
	int insertQuestion(@Param("paramap") Map<String, Object> paramap);
	
	// question one
	Question selectQuestionOne(Question question);
	
	// quest List 
	List<Question> selectQuestionList(int testNo);

}

package goodee.gdj58.online.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import goodee.gdj58.online.vo.Example;

@Mapper
public interface ExampleMapper {
	
	// example 수정
	int updateExample(Example exmple);
	
	// example 삭제
	int deleteExample(int questionNo);
	
	// example 추가
	int insertExample(Map<String, Object> paramap);
	
	// example list
	List<Example> selectExampleList(int questionNo);
	
}

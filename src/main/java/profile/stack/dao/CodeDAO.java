package profile.stack.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import profile.common.ServiceResult;
import profile.vo.CodeVO;

/**
 * 프로그램 설명
 * @date        : 2024. 3. 11.
 * @author      : boyoung
 * @version	: 1.0
 * <PRE>
 * ----------------------------
 * 개정이력
 * 2024. 3. 11. boyoung : 최초작성
 * </PRE>
 */

@Mapper
public interface CodeDAO {

	List<CodeVO> selectCodeTitleList(@Param("stackNo")int stackNo);

	CodeVO selectCodeDetail(@Param("codeNo")int codeNo);

	int insertCode(CodeVO cVO);

	int updateCode(CodeVO cVO);

	int deleteCode(int codeNo);

}

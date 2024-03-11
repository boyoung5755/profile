package profile.stack.service;

import java.util.List;

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


public interface CodeService {

	/**
	 * 메서드 설명 -> 기술에 따른 코드제목 가져오기
	 * @Method Name  	: retrieveCodeNameList
	 * @date   			: 2024. 3. 11.
	 * @author   		: boyoung
	 * @version     	: 1.0
	 * ----------------------------------------
	 * @return
	 */
	List<CodeVO> retrieveCodeTitleList(int stackNo);

}

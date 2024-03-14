package profile.stack.service;

import java.util.List;

import profile.vo.CodeVO;
import profile.vo.StackVO;

/**
 * 프로그램 설명
 * @date        : 2024. 3. 8.
 * @author      : boyoung
 * @version	: 1.0
 * <PRE>
 * ----------------------------
 * 개정이력
 * 2024. 3. 8. boyoung : 최초작성
 * </PRE>
 */
public interface StackService {

	public List<StackVO> testselect();

	/**
	 * 메서드 설명 나의 스택목록을 조회하는 메서드
	 * @Method Name  	: retrieveStackList
	 * @date   			: 2024. 3. 11.
	 * @author   		: boyoung
	 * @version     	: 1.0
	 * ----------------------------------------
	 * @return
	 */
	public List<StackVO> retrieveStackList();

	
	/**
	* 메서드 설명 ->  스택과 코드의 리스트
	* @Method Name  	: retrieveStackCodeList
	* @date   			: 2024. 3. 13.
	* @author   		: boyoung
	* @version     	: 1.0
	* ----------------------------------------
	* @return
	*/
	public List<CodeVO> retrieveStackCodeList();

}

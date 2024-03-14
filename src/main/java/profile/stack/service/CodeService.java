package profile.stack.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

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


	/**
	 * 메서드 설명 -> 코드제목에 따른 코드상세 가져오기
	 * @Method Name  	: retrieveCode
	 * @date   			: 2024. 3. 11.
	 * @author   		: boyoung
	 * @version     	: 1.0
	 * ----------------------------------------
	 * @return
	 */
	CodeVO retrieveCode(int codeNo);


	/**
	 * 메서드 설명 -> 코드 등록하기
	 * @Method Name  	: createCode
	 * @date   			: 2024. 3. 11.
	 * @author   		: boyoung
	 * @version     	: 1.0
	 * ----------------------------------------
	 * @return
	 */
	ServiceResult createCode(CodeVO cVO);


	/**
	* 메서드 설명  -> 코드 수정하기
	* @Method Name  	: modifyCode
	* @date   			: 2024. 3. 11.
	* @author   		: boyoung
	* @version     	: 1.0
	* ----------------------------------------
	* @return
	*/
	ServiceResult modifyCode(CodeVO cVO);


	/**
	* 메서드 설명 -> 코드 삭제하기 (삭제여부 업데이트 방식)
	* @Method Name  	: deleteCode
	* @date   			: 2024. 3. 11.
	* @author   		: boyoung
	* @version     	: 1.0
	* ----------------------------------------
	* @return
	*/
	ServiceResult deleteCode(int codeNo);


	ServiceResult createFileUpload(MultipartFile[] profileFile, String fileCode);

}

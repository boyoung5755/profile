package profile.member.service;

import profile.common.ServiceResult;
import profile.vo.MemberVO;

/**
 * 프로그램 설명
 * @date        : 2024. 3. 20.
 * @author      : boyoung
 * @version	: 1.0
 * <PRE>
 * ----------------------------
 * 개정이력
 * 2024. 3. 20. boyoung : 최초작성
 * </PRE>
 */


public interface MemberService {

	/**
	 * 메서드 설명->카카오 정보로 회원 등록하기
	 * @Method Name  	: kakaoJoin
	 * @date   			: 2024. 3. 20.
	 * @author   		: boyoung
	 * @version     	: 1.0
	 * ----------------------------------------
	 * @param mVO
	 * @return
	 */
	ServiceResult kakaoJoin(MemberVO mVO);

}

package profile.member.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
@Mapper
public interface MemberDAO {

	/**
	 * 메서드 설명 -> 회원찾기
	 * @Method Name  	: selectMember
	 * @date   			: 2024. 3. 20.
	 * @author   		: boyoung
	 * @version     	: 1.0
	 * ----------------------------------------
	 * @param mVO
	 */
	MemberVO selectMember(@Param ("memEmail")String mail);

	int memberKakaoJoin(MemberVO mVO);

}

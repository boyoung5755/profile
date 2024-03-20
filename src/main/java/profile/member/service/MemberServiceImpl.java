package profile.member.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import profile.common.ServiceResult;
import profile.member.dao.MemberDAO;
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
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{
	
	private final MemberDAO dao;

	@Override
	public ServiceResult kakaoJoin(MemberVO mVO) {
		
		mVO.setMemRdate(LocalDateTime.now());
		mVO.setMemRole("member");
		int cnt = dao.memberKakaoJoin(mVO);
		ServiceResult result;
		if(cnt >=1) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAIL;
		}
		return result;
	}

}

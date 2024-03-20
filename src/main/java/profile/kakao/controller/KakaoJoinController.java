package profile.kakao.controller;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import profile.common.ServiceResult;
import profile.member.service.MemberService;
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
@Slf4j
@RestController
@RequestMapping("/kakao")
public class KakaoJoinController {

	
	@Inject
	private MemberService mservice; 
	
	//새로운 코드 등록하기
	@PostMapping("/join")
	public Map<String, String> kakaoJoin(
		MemberVO mVO
		,HttpSession session
		){
		Map<String, String> map  = new HashMap<>();
		ServiceResult result = mservice.kakaoJoin(mVO);
		if(result.equals(ServiceResult.OK)) {
			map.put("success","Y");
			session.setAttribute("role", "member");
		}else {
			map.put("success","N");
		}
		return map;
	}
}

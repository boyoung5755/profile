package profile.kakao.controller;

import java.util.HashMap;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;
import profile.kakao.service.KakaoService;

/**
 * 프로그램 설명
 * @date        : 2024. 3. 19.
 * @author      : boyoung
 * @version	: 1.0
 * <PRE>
 * ----------------------------
 * 개정이력
 * 2024. 3. 19. boyoung : 최초작성
 * </PRE>
 */
@Slf4j
@Controller
@RequestMapping("/kakao")
public class KaKaoController {
	
	@Inject
	private KakaoService service;
	
	
	
	@GetMapping("/login")
	public String kakaoLogin(
		@RequestParam(required = false) String code
		, HttpSession session
		){
	   
		//1. 인가코드 받기 @RequestParam(required = false) String code
		
		log.info(">>>>>>>>>>>>>>>>>>인가코드>>>>>>>>>>>>>>>>>>>>>>>>>>>"+code);
	
        // URL에 포함된 code를 이용하여 액세스 토큰 발급
        String accessToken = service.getKakaoAccessToken(code);

        // 액세스 토큰을 이용하여 카카오 서버에서 유저 정보(닉네임, 이메일) 받아오기
        HashMap<String, Object> userInfo = service.getUserInfo(accessToken);
        log.info("login Controller : " + userInfo);
        
        String nickName = (String) userInfo.get("nickname");
        
        if("김보영".equals(nickName)) {
        	session.setAttribute("role", "admin");
        }
        
        return "redirect:/common/menu";
		 
	}

}

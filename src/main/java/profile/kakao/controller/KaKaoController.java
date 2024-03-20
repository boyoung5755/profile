package profile.kakao.controller;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.extern.slf4j.Slf4j;
import profile.common.ServiceResult;
import profile.kakao.service.KakaoService;
import profile.member.service.MemberService;
import profile.vo.CodeVO;
import profile.vo.MemberVO;
import profile.vo.StackVO;

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
		, Model model
		, RedirectAttributes ra
		){
		
		String returnPage= "";
	   
		//1. 인가코드 받기 @RequestParam(required = false) String code
		
		log.info(">>>>>>>>>>>>>>>>>>인가코드>>>>>>>>>>>>>>>>>>>>>>>>>>>"+code);
	
        // URL에 포함된 code를 이용하여 액세스 토큰 발급
        String accessToken = service.getKakaoAccessToken(code);

        // 액세스 토큰을 이용하여 카카오 서버에서 유저 정보(닉네임, 이메일) 받아오기
        MemberVO mVO = service.getUserInfo(accessToken);
        log.info("카카오로 로그인한 사람의 정보 : >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + mVO);
        
        //로그인한 사람이 가입여부와 role 에 따라 다르게 
        if("admin".equals(mVO.getMemRole())) {
        	session.setAttribute("role", "admin");
        	ra.addFlashAttribute("msg","환영합니다 관리자님 !!");
        	ra.addFlashAttribute("url","/common/menu");
        	returnPage = "redirect:/alert";
        	log.info("url location.href//role admin");
        }else if ("member".equals(mVO.getMemRole())) {
        	session.setAttribute("role", "member");
        	ra.addFlashAttribute("msg","안녕하세요 "+mVO.getMemNickname()+"님 !!");
        	ra.addFlashAttribute("url","/common/menu");
        	returnPage = "redirect:/alert";
        	log.info("url location.href//role member");
        }else {
        	ra.addFlashAttribute("msg","등록된 회원이 아닙니다. 가입을 진행합니다.");
        	ra.addFlashAttribute("newMember" ,mVO);
        	returnPage = "redirect:/member/join";
        	log.info("url /member/join//role new");
        } 
        
        return returnPage;
		 
	}

}

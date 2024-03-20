package profile.common.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

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

@Slf4j
@RestController
public class AdminController {
	
	@PostMapping("/logout")
	public Map<String, String> logout(HttpSession session){
		Map<String, String> map = new HashMap<>();
		
		//session.removeAttribute("role");
		session.invalidate();
		map.put("msg","연결이 해제되었습니다.");
		return map;
	}
	
	
	
	@PostMapping("/admin")
	public Map<String, Object> adminCheck(
		@RequestParam(value="password") String password
		, HttpSession session
		) {
		Map<String, Object> map = new HashMap<>();
		log.info("입력된 관리자 비밀번호"+password);
		//비밀번호 확인
		if(password.equals("1234")) {
			//세션에 정보 담아주기
			session.setAttribute("role", "admin");

			map.put("msg","환영합니다. 관리자님");
		}else {
			map.put("msg","접근권한이 없습니다.");
		}
		return map;
	}
 
}

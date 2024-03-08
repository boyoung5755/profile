package profile.common.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

@RestController
public class AdminController {
	
	
	@PostMapping("/admin")
	public Map<String, String> adminCheck(
		@RequestParam(value="password") String password
		, HttpSession session
		) {
		Map<String, String> map = new HashMap<>();
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

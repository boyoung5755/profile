package profile.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 프로그램 설명 -> 단순 뷰를 호출하는 controller들
 * @date        : 2024. 3. 8.
 * @author      : boyoung
 * @version	: 1.0
 * <PRE>
 * ----------------------------
 * 개정이력
 * 2024. 3. 8. boyoung : 최초작성
 * </PRE>
 */
@Controller
public class ViewController {
	
	
	@GetMapping("/menu")
	public String menu() {
		return "common/menu";
	}

}

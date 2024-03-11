package profile.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
@RequestMapping
public class ViewController {
	
	@GetMapping("common/profile")
	public String profile(){return "/common/profile";}
	
	@GetMapping("guestbook")
	public String guestbookPage(){return "/guestbook/guestbookHome";}
	
	@GetMapping("gallery")
	public String galleryPage(){return "/gallery/galleryHome";}
	
	@GetMapping("myStack")
	public String myStackPage(){return "/stack/stackHome";}
	
	@GetMapping("common/menu")
	public String menu() {return "/common/menu";}

	@GetMapping("common/home")
	public String home() {return "/common/home";}
}

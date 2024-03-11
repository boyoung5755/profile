package profile.common.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import profile.stack.service.StackService;
import profile.vo.StackVO;

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
@Slf4j
public class ViewController {
	
	@Inject
	private StackService service;
	
	@GetMapping("common/profile")
	public String profile(){return "/common/profile";}
	
	@GetMapping("guestbook")
	public String guestbookPage(){return "/guestbook/guestbookHome";}
	
	@GetMapping("gallery")
	public String galleryPage(){return "/gallery/galleryHome";}
	
	@GetMapping("stack")
	public String myStackPage(Model model){
		
		//나의 스택리스트 보내기
		List<StackVO> sVO = service.retrieveStackList();
		model.addAttribute("stackList",sVO);
		log.info("stackList", sVO);
		
		return "/stack/stackHome";
	}
	
	@GetMapping("common/menu")
	public String menu() {return "/common/menu";}

	@GetMapping("common/home")
	public String home() {return "/common/home";}
	
}

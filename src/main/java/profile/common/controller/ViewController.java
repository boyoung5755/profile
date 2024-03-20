package profile.common.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;
import profile.gallery.service.GalleryService;
import profile.info.service.InfoService;
import profile.stack.service.CodeService;
import profile.stack.service.StackService;
import profile.vo.FileVO;
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
	
	@Inject
	private CodeService cService;
	
	@Inject
	private GalleryService gService;
	
	@Inject
	private InfoService iService;
	
	
	@GetMapping("common/profile")
	public String profile(Model model){
		model.addAttribute("stackList",service.retrieveStackList());
		model.addAttribute("rndStr", createRandomStrUsingUtilsRand()); 
		return "/common/profile";
		
	}
	
	@GetMapping("guestbook")
	public String guestbookPage(){return "/guestbook/guestbookHome";}
	
	@GetMapping("alert")
	public String alert(){return "/alert";}
	
	@GetMapping("member/join")
	public String memberJoin(){return "/member/join";}
	
	@GetMapping("gallery")
	public String galleryPage(Model model , HttpServletResponse resp){
		
		List<FileVO> fVO = gService.retrieveGalleryList();
		
		model.addAttribute("galleryFileList", fVO);
		model.addAttribute("rndStr", createRandomStrUsingUtilsRand()); 
		return "/gallery/galleryHome";
	}
	
	@GetMapping("stack")
	public String myStackPage(Model model){
		
		//나의 스택리스트 보내기
		List<StackVO> sVO = service.retrieveStackList();
		model.addAttribute("stackList",sVO);
		log.info("stackList", sVO);
		return "/stack/stackHome";
	}
	
	@GetMapping("stack/manage")
	public String stackManage(Model model){
		
		//나의 스택리스트 보내기
		List<StackVO> sVO = service.retrieveStackList();
		model.addAttribute("stackList",sVO);
		log.info("stackList", sVO);
		return "/stack/manage";
	}
	
	@GetMapping("stack/form")
	public String stackForm(
		Model model
		,@RequestParam(required = false) Integer codeNo
		) {
		//코드 목록 보내기 
		List<StackVO> sVO = service.retrieveStackList();
		model.addAttribute("stackList",sVO);
		log.info("stackList", sVO);
		
		//랜덤문자보내기
		model.addAttribute("rndStr", createRandomStrUsingUtilsRand());
		
		return "/stack/stackForm";
	}
	
	
	@GetMapping("stack/edit/{codeNo}")
	public String stackEdit(
		Model model
		,@PathVariable int codeNo
		) {
		//코드 디테일 보내기
		model.addAttribute("detail" ,cService.retrieveCode(codeNo));
		log.info("codeDetail : ",cService.retrieveCode(codeNo));
		
		//코드 목록 보내기 
		List<StackVO> sVO = service.retrieveStackList();
		model.addAttribute("stackList",sVO);
		log.info("stackList", sVO);
		return "/stack/stackEdit";
	}
	
	
	@GetMapping("common/menu")
	public String menu() {return "/common/menu";}

	@GetMapping("common/home")
	public String home() {return "/common/home";}
	
	
	//파일과 게시판의 저장될 랜덤번호
	public String createRandomStrUsingUtilsRand() {
		boolean useLetters = true; //랜덤 문자열에 영문자 (알파벳)가 포함
		boolean useNumbers = true; //랜덤 문자열에 숫자가 포함
		//랜덤문자자릿수
		int randomStrLen = 15;
		String randomStr = RandomStringUtils.random(randomStrLen, useLetters, useNumbers);
			
		System.out.println("생성된 랜덤문자열 : " + randomStr);
		return randomStr;
	}
	
}

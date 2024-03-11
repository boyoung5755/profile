package profile.guestbook.controller;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import profile.common.Utils;
import profile.guestbook.service.GuestbookService;
import profile.vo.GuestbookVO;

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
@RequestMapping("/gb")
@RequiredArgsConstructor
public class GuestbookController {
	
	
	@Inject
	private GuestbookService service;
	
	private Utils utils;
	
	
	//방명록 등록하기
	@PostMapping("/createGB")
    public Map<String, String> createGB(
    	@ModelAttribute("guestbook") GuestbookVO gbVO
    	) {
        Map<String, String> map = new HashMap<>();
        utils.sendResult(service.createGB(gbVO));
        return map;
    }
	
	
	//방명록 수정하기
	@PutMapping("/modifyGB")
    public Map<String, String> modifyGB(
    	@ModelAttribute("guestbook") GuestbookVO gbVO
    	) {
        Map<String, String> map = new HashMap<>();
        utils.sendResult(service.modifyGB(gbVO));
        return map;
    }
	
	
	//방명록 삭제하기
	@DeleteMapping("/removeGB")
    public Map<String, String> removeGB(
    	@ModelAttribute("guestbook") GuestbookVO gbVO
    	) {
        Map<String, String> map = new HashMap<>();
        utils.sendResult(service.removeGB(gbVO));
        return map;
    }
	
	
	
	
	

}

package profile.gallery.controller;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import profile.common.ServiceResult;
import profile.gallery.service.GalleryService;
import profile.vo.GalleryVO;

/**
 * 프로그램 설명
 * @date        : 2024. 3. 12.
 * @author      : boyoung
 * @version	: 1.0
 * <PRE>
 * ----------------------------
 * 개정이력
 * 2024. 3. 12. boyoung : 최초작성
 * </PRE>
 */
@Slf4j
@RestController
@RequestMapping("/gallery")
public class GalleryController {
	
	@Inject
	private GalleryService service;
	
	//새로운 코드 등록하기
	@PostMapping("/upload")
	public Map<String, String> upload(
		GalleryVO gVO
		){
		Map<String, String> map  = new HashMap<>();
		ServiceResult result = service.upload(gVO);
		log.info("갤러리 파일 등록 성공===================>>>"+gVO.getFileCode());
		if(result.equals(ServiceResult.OK)) {
			map.put("success","Y");
		}else {
			map.put("success","N");
		}
		return map;
	}
	

	
	
}

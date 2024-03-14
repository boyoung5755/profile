package profile.info.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import profile.common.ServiceResult;
import profile.info.service.InfoService;
import profile.vo.CodeVO;
import profile.vo.FileVO;

/**
 * 프로그램 설명
 * @date        : 2024. 3. 13.
 * @author      : boyoung
 * @version	: 1.0
 * <PRE>
 * ----------------------------
 * 개정이력
 * 2024. 3. 13. boyoung : 최초작성
 * </PRE>
 */

@Slf4j
@RestController
@RequestMapping("/info")
public class InfoController {
	
	@Inject
	private InfoService service;
	
	//새로운 코드 등록하기
	@PostMapping("/addImg")
	public Map<String, String> addImg(
		FileVO fVO
		){
		Map<String, String> map  = new HashMap<>();
		ServiceResult result = service.addImg(fVO);
		if(result.equals(ServiceResult.OK)) {
			map.put("success","Y");
		}else {
			map.put("success","N");
		}
		return map;
	}
	
	
	//차트 정보 가져오기
	@PostMapping("/getChart")
	public Map<String,List<CodeVO>> getChart(){
		Map<String, List<CodeVO>> map  = new HashMap<>();
		//차트 정보가져오기
		List<CodeVO> cVO = service.getChart();
	
		map.put("chart", cVO); 
		return map;
	}

}

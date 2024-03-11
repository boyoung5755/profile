package profile.stack.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import profile.common.Utils;
import profile.stack.service.CodeService;
import profile.vo.CodeVO;

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
@RequestMapping("/stack")
public class StackController {
	
	@Inject
	private CodeService service;
	
	private Utils utils;

	//기술에 따른 코드제목 가져오기
	@GetMapping("/{stackNo}")
	public Map<String, List<CodeVO>> retrieveCodeTitleList(
		@PathVariable int stackNo
		){
		Map<String, List<CodeVO>> map = new HashMap<>();
		map.put("cnameList",service.retrieveCodeTitleList(stackNo));
		return map;
	}

}

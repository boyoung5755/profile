package profile.stack.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import profile.common.ServiceResult;
import profile.stack.service.CodeService;
import profile.stack.service.StackService;
import profile.vo.CodeVO;
import profile.vo.StackVO;

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
@RequestMapping("/stack")
public class StackController {
	
	@Inject
	private CodeService service;
	
	@Inject
	private StackService sService;
	
	
	
	//스택리스트
	@PostMapping("/stackList")
	public Map<String, List<CodeVO>> stackList(){
		
		Map<String, List<CodeVO>> map = new HashMap<>();
		map.put("stackList", sService.retrieveStackCodeList());
		return  map;
	}
	
	

	//기술에 따른 코드제목 가져오기
	@GetMapping("/get/{stackNo}")
	public Map<String, List<CodeVO>> retrieveCodeTitleList(
		@PathVariable int stackNo
		){
		Map<String, List<CodeVO>> map = new HashMap<>();
		map.put("cnameList",service.retrieveCodeTitleList(stackNo));
		return map;
	}
	
	//코드 상세가져오기
	@GetMapping("/detail/{codeNo}")
	public Map<String, CodeVO> retrieveCode(
		@PathVariable int codeNo
		){
		Map<String, CodeVO> map  = new HashMap<>();
		map.put("codeDetail", service.retrieveCode(codeNo));
		log.info("codeDetail : ",service.retrieveCode(codeNo));
		return map;
	}
	
	
	//새로운 코드 등록하기
	@PostMapping("/codeNew")
	public Map<String, String> createCode(
		CodeVO cVO
		, StackVO sVO
		){
		Map<String, String> map  = new HashMap<>();
		cVO.setStackNo(sVO.getStackNo());
		ServiceResult result = service.createCode(cVO);
		if(result.equals(ServiceResult.OK)) {
			map.put("success","Y");
		}else {
			map.put("success","N");
		}
		return map;
	}

	//코드 삭제하기 
	@PutMapping("/delete/{codeNo}")
	public Map<String, String> deleteCode(
			@PathVariable int codeNo
			){
		Map<String, String> map  = new HashMap<>();
		ServiceResult result = service.deleteCode(codeNo);
		if(result.equals(ServiceResult.OK)) {
			map.put("success","Y");
		}else {
			map.put("success","N");
		}
		return map;
	}
	
	//코드 수정하기
	@PutMapping("/update")
	public Map<String, String> modifyCode(
			@RequestBody CodeVO cVO
			){
		Map<String, String> map  = new HashMap<>();
		ServiceResult result = service.modifyCode(cVO);
		if(result.equals(ServiceResult.OK)) {
			map.put("success","Y");
		}else {
			map.put("success","N");
		}
		return map;
	}
	

}

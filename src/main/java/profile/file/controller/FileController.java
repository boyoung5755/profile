package profile.file.controller;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import profile.file.service.FileService;
import profile.vo.FileVO;
import profile.vo.PaginationInfo;
import profile.vo.SearchVO;

/**
 * 프로그램 설명
 * @date        : 2024. 3. 18.
 * @author      : boyoung
 * @version	: 1.0
 * <PRE>
 * ----------------------------
 * 개정이력
 * 2024. 3. 18. boyoung : 최초작성
 * </PRE>
 */
@RestController
@RequestMapping("/file")
public class FileController {
	
	@Value("#{appInfo.codeFiles}")
	private Resource codeFiles;
	
	@Value("#{appInfo.codeImagesUrl}")
	private String codeImagesUrl;
	
	@Value("#{appInfo.codeImagesUrl}")
	private Resource codeImages;	//폴더는 리소스로 받아와야함
	
	
	@Inject
	private FileService service;

	

	@GetMapping("/list")
	public Map<String,PaginationInfo<FileVO>> boardList(
			@RequestParam(value = "page", required = false, defaultValue = "1") int currentPage
			, @RequestParam(value = "listNum", required = false, defaultValue = "5") int listNum
			, @ModelAttribute("simpleCondition") SearchVO simpleCondition	
		){
		
		Map<String, PaginationInfo<FileVO>> map = new HashMap<String, PaginationInfo<FileVO>>();
		
		FileVO file = new FileVO();
		
		//페이지
		PaginationInfo<FileVO> paging = new PaginationInfo<FileVO>(listNum,3);
		paging.setDetailCondition(file);	//키워드 검색 조건
		paging.setSimpleCondition(simpleCondition);
		paging.setCurrentPage(currentPage);
		paging.setScreenSize(listNum);
		
		paging.setListNum(listNum);
		
		service.retrieveFileList(paging);
		map.put("paging", paging);
		

		return map;
	}
	
}

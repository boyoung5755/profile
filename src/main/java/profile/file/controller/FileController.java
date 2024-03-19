package profile.file.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import profile.common.ServiceResult;
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

@Slf4j
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

	@DeleteMapping("/removeFile")
	public Map<String, String> removeFile(
		@RequestBody FileVO fVO
		) throws IOException {
		Map<String, String> map = new HashMap<String, String>();
		ServiceResult result = service.removeFile(fVO);
		
		ResponseEntity<Boolean> flag = removeFile(fVO.getFileSn());
		
		if(ServiceResult.OK.equals(result)  && flag.getStatusCode() == HttpStatus.OK) {
			log.info("파일삭제");
			map.put("success", "Y");
			
		}else {
			map.put("success", "N");
		}
	
		return map;
	}
	
	
    public ResponseEntity<Boolean> removeFile(String fileName) throws IOException{

        String srcFileName = null;

        try{
            srcFileName = URLDecoder.decode(fileName,"UTF-8");
            //UUID가 포함된 파일이름을 디코딩해줍니다.
            File file = new File(codeFiles.getFile().getAbsolutePath()+File.separatorChar+srcFileName);
            log.info("삭제되는 파일 >>>>>>>>"+codeFiles.getFile().getAbsolutePath()+"/"+srcFileName);
            boolean result = file.delete();

            return new ResponseEntity<>(result,HttpStatus.OK);
        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
            return new ResponseEntity<>(false,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
	
	
	

	@GetMapping("/list")
	public Map<String,PaginationInfo<FileVO>> fileList(
			@RequestParam(value = "page", required = false, defaultValue = "1") int currentPage
			, @RequestParam(value = "listNum", required = false, defaultValue = "5") int listNum
			, @ModelAttribute("simpleCondition") SearchVO simpleCondition	
		){
		
		log.info(simpleCondition.getSearchType()+"에서 뭘 검색햇누!!@!>>>>>>>>>>>>"+simpleCondition.getSearchWord());
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
	
	
	
	//파일 다운로드
	@RequestMapping("/download")
	public ResponseEntity<InputStreamResource> downloadBoardFile(
			HttpServletResponse response
			, FileVO file
			) throws IOException {
		
		//파일 조회
		FileVO oneFile= service.retrieveFile(file);
		File tmpFile = new File(codeFiles.getFile().getAbsolutePath()+File.separatorChar+oneFile.getFileSn());
		
		InputStream res = new FileInputStream(tmpFile) {
			@Override
			public void close() throws IOException {
				super.close();
			}
		};

		// 응답 생성
		return ResponseEntity.ok().contentLength(tmpFile.length())
				.contentType(MediaType.parseMediaType(oneFile.getFileMime())) // 파일에 맞는 컨텐츠 타입 지정
				.header("Content-Disposition", "attachment;filename="+URLEncoder.encode(oneFile.getFileName(),"UTF-8")) // 파일 이름 설정
				.body(new InputStreamResource(res));
	}
	
}

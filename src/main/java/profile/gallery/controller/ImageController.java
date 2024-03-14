package profile.gallery.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Collections;
import java.util.UUID;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;
import profile.gallery.service.GalleryService;
import profile.info.service.InfoService;
import profile.vo.FileVO;

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
@Controller
public class ImageController {
	
	
	@Value("#{appInfo.codeFiles}")
	private Resource codeFiles;
	
	@Value("#{appInfo.codeImagesUrl}")
	private String codeImagesUrl;
	
	@Value("#{appInfo.codeImagesUrl}")
	private Resource codeImages;	//폴더는 리소스로 받아와야함
	
	@Inject
	private GalleryService gService;
	
	@Inject
	private InfoService iService;
	
	//ck에디터 이미지 업로드
	@PostMapping("image")
	public String imageUpload(MultipartFile upload, Model model, HttpServletRequest req) throws IOException {
	
		if(!upload.isEmpty()) {	//파일이 정상적으로 업로드 되고 있다.
			// 로컬의 파일의 url이 필요하므로 webResource형태로 저장이 되야함
			String saveName = UUID.randomUUID().toString(); //중복되지 않도록 uuid사용
			File saveFolder =  codeImages.getFile(); //파일로 만들어줘야 저장이 가능
			File saveFile = new File(saveFolder, saveName);
			upload.transferTo(saveFile);	// upload 완료
			
			String url = req.getContextPath()+codeImagesUrl + "/" + saveName;	// 이거 비동기의 응답데이터로 나가야함
			model.addAttribute("uploaded",1);
			model.addAttribute("fileName",upload.getOriginalFilename());	//원본파일명
			model.addAttribute("url",url);
		
		} else {	// 업로드가 되지 않았을때.
			model.addAttribute("uploaded",0);
			model.addAttribute("error", Collections.singletonMap("message", "업로드 된 파일 없음"));	// map의 엔트리가 1개밖에 없을 때 사용
		}
		
		return "jsonView";
	}
	
	//img파일 썸네일 
	@GetMapping("/image/show")
	@ResponseBody
	public ResponseEntity<byte[]> getFile(String fileNo){	
		FileVO fileVO = gService.selectFileName(fileNo);
	    File file=new File(fileVO.getFilePath()+File.separatorChar+fileVO.getFileSn());
	    log.info("파일경로>>>>>>>>>>>>>>>>>"+file.getPath());
	    ResponseEntity<byte[]> result=null;
	    try {
	        HttpHeaders headers=new HttpHeaders();
	        headers.add("Content-Type", Files.probeContentType(file.toPath()));
	        result=new ResponseEntity<>(FileCopyUtils.copyToByteArray(file),headers,HttpStatus.OK );
	    }catch (IOException e) {
	        e.printStackTrace();
	    }
	    return result;
	}
	
	

	//프로필 img파일
	@GetMapping("/image/profileShow")
	@ResponseBody
	public ResponseEntity<byte[]> getFile(){	
		FileVO fileVO = iService.selectProfileImg();
		File file=new File(fileVO.getFilePath()+File.separatorChar+fileVO.getFileSn());
		log.info("파일경로>>>>>>>>>>>>>>>>>"+file.getPath());
		ResponseEntity<byte[]> result=null;
		try {
			HttpHeaders headers=new HttpHeaders();
			headers.add("Content-Type", Files.probeContentType(file.toPath()));
			result=new ResponseEntity<>(FileCopyUtils.copyToByteArray(file),headers,HttpStatus.OK );
		}catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
}

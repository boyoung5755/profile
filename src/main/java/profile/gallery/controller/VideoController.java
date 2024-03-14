package profile.gallery.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.inject.Inject;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import profile.gallery.service.GalleryService;
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

@RestController
public class VideoController {
	
	@Inject
	private GalleryService gService;

	@GetMapping(path = "/video", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public Resource video(String fileNo) throws FileNotFoundException, IOException {
		FileVO fileVO = gService.selectFileName(fileNo);
        File file = new File(fileVO.getFilePath()+File.separatorChar+fileVO.getFileSn());
	    return new ByteArrayResource(FileCopyUtils.copyToByteArray(new FileInputStream(file)));
	}
}

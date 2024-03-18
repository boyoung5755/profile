package profile.gallery.service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import profile.common.ServiceResult;
import profile.file.dao.FileDAO;
import profile.gallery.dao.GalleryDAO;
import profile.vo.FileVO;
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
@Service
@RequiredArgsConstructor
public class GalleryServiceImpl implements GalleryService{

	
	private final FileDAO fdao;
	private final GalleryDAO dao;
	
	@Value("#{appInfo.codeFiles}")
	private Resource codeFiles	;
	


	//파일등록
	private void processProfileFiles(GalleryVO gVO) {
		
		List<FileVO> fileList = gVO.getFileList();
		if(fileList!=null) {
			fileList.forEach((atch)->{
				try {
					atch.setFileCode(gVO.getFileCode());
					atch.setFileRdate(LocalDateTime.now());
					atch.setFilePath("C:"+File.separatorChar+"code");
					fdao.insertProfileFile(atch);
					atch.saveTo(codeFiles.getFile());
				}catch (IOException e) {
					throw new RuntimeException(e);
				}
			});
		}
	}



	@Override
	public ServiceResult upload(GalleryVO gVO) {
			try {
				processProfileFiles(gVO);
			} catch (Exception e) {
			}
			gVO.setImgRdate(LocalDateTime.now());
			log.info("갤러리 등록 현재시간========>>", gVO.getImgRdate());
			gVO.setImgDel("N");
			
			int cnt = dao.insertGallery(gVO);
			ServiceResult result;
			if(cnt >=1) {
				result = ServiceResult.OK;
			}else {
				result = ServiceResult.FAIL;
			}
			return result;
	}



	@Override
	public List<FileVO> retrieveGalleryList(){
		
		return dao.selectGalleryList();
	}



	@Override
	public FileVO selectFileName(String fileCode) {
		// TODO Auto-generated method stub
		return fdao.selectFileName(fileCode);
	}
	
	
	
	
	
	
	
	
}

package profile.info.service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import profile.common.ServiceResult;
import profile.file.dao.FileDAO;
import profile.gallery.dao.GalleryDAO;
import profile.info.dao.InfoDAO;
import profile.vo.CodeVO;
import profile.vo.FileVO;
import profile.vo.GalleryVO;

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
@Service
@RequiredArgsConstructor
public class InfoServiceImpl implements InfoService{
	
	private final InfoDAO dao;
	private final FileDAO fdao;
	
	@Value("#{appInfo.codeFiles}")
	private Resource codeFiles	;

	
	@Override
	public ServiceResult addImg(FileVO fVO) {
		
		//1.우선 기존에 있는 프로필 이미지의 게시여부를 
		//  N으로 업데이트 
		dao.changeShow();
		ServiceResult result;
		//2.등록한 이미지를 게시여부 "Y"로 인서트
		try {
			processProfileFiles(fVO);
			result = ServiceResult.OK;
		} catch (Exception e) {
			result = ServiceResult.FAIL;
		}
	
		return result;
	}
	

	@Override
	public FileVO selectProfileImg() {
		return dao.selectProfileImg();
	}

	
	//이미지파일등록
	private void processProfileFiles(FileVO fVO) {
		
		try {
			fVO.setFileName(fVO.getProfileFile().getOriginalFilename());
			fVO.setFileMime(fVO.getProfileFile().getContentType());
			fVO.setFileFancysize(FileUtils.byteCountToDisplaySize(fVO.getProfileFile().getSize()));
			fVO.setFileSn(UUID.randomUUID().toString());
			fVO.setFileRdate(LocalDateTime.now());
			fVO.setShowProfileimg("Y");
			fVO.setFilePath("C:"+File.separatorChar+"code");
			fdao.insertProfileFile(fVO);
			fVO.saveTo(codeFiles.getFile());
			
		} catch (IOException e) {
			throw new RuntimeException(e);
			
		}
	}


	@Override
	public List<CodeVO>  getChart() {
		// TODO Auto-generated method stub
		return dao.getChart();
	}
	
	
	

	
}

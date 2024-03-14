package profile.stack.service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import profile.common.ServiceResult;
import profile.file.dao.FileDAO;
import profile.stack.dao.CodeDAO;
import profile.vo.CodeVO;
import profile.vo.FileVO;

/**
 * 프로그램 설명
 * @date        : 2024. 3. 11.
 * @author      : boyoung
 * @version	: 1.0
 * <PRE>
 * ----------------------------
 * 개정이력
 * 2024. 3. 11. boyoung : 최초작성
 * </PRE>
 */

@Service
@RequiredArgsConstructor
public class CodeServiceImpl implements CodeService {
	
	
	private final CodeDAO dao;
	
	private final FileDAO fdao;
	
	@Value("#{appInfo.codeFiles}")
	private Resource codeFiles	;
	
	

	@Override
	public List<CodeVO> retrieveCodeTitleList(int stackNo) {
		return dao.selectCodeTitleList(stackNo);
	}

	@Override
	public CodeVO retrieveCode(int codeNo) {
		return dao.selectCodeDetail(codeNo);
	}

	@Override
	public ServiceResult createCode(CodeVO cVO) {
		
		cVO.setCodeImage(makeImageSrc(cVO.getCodeContent()));
		
		
		try {
			processProfileFiles(cVO);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		int cnt = dao.insertCode(cVO);
		ServiceResult result;
		if(cnt >=1) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAIL;
		}
		return result;
	}

	@Override
	public ServiceResult modifyCode(CodeVO cVO) {
		cVO.setCodeImage(makeImageSrc(cVO.getCodeContent()));
		int cnt = dao.updateCode(cVO);
		ServiceResult result;
		if(cnt >=1) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAIL;
		}
		return result;
	}

	@Override
	public ServiceResult deleteCode(int codeNo) {
		int cnt = dao.deleteCode(codeNo);
		ServiceResult result;
		if(cnt >=1) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAIL;
		}
		return result;
	}
	
	
	private String makeImageSrc(String makeImageSrc) {
		
		String imageUrl = "";
		String regex = "src=\"([^\"]+)\"";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(makeImageSrc);
        
        // 매칭된 부분이 있다면
        if (matcher.find()) {
            // src 속성의 값 가져오기
            imageUrl = matcher.group(1);
            System.out.println("-----------------이미지URL----------------------->>"+imageUrl);
        } else {
            System.out.println("No match found.");
        }
		return imageUrl;
	}
	
	
	//파일등록
	private void processProfileFiles(CodeVO cVO) {
		
		List<FileVO> fileList = cVO.getFileList();
		if(fileList!=null) {
			fileList.forEach((atch)->{
				try {
					atch.setFileCode(cVO.getFileCode());
					atch.setFileRdate(LocalDateTime.now());
					atch.setFilePath("D:"+File.separatorChar+"code");
					fdao.insertProfileFile(atch);
					atch.saveTo(codeFiles.getFile());
				}catch (IOException e) {
					throw new RuntimeException(e);
				}
			});
		}
	}
	
	
	@Override
	public ServiceResult createFileUpload(MultipartFile[] profileFile, String fileCode) {
		
		CodeVO cVO = new CodeVO();
		cVO.setProfileFile(profileFile);
		cVO.setFileCode(fileCode);
		
		ServiceResult result= null;
		//파일 등록
		try {
			processProfileFiles(cVO);
			result = ServiceResult.OK;
		} catch (Exception e) {
			// TODO: handle exception
			result = ServiceResult.FAIL;
		}
		
		return result;
	}
	
	
	
	
	
	

}

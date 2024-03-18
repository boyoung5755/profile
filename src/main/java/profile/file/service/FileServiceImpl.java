package profile.file.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import profile.common.ServiceResult;
import profile.file.dao.FileDAO;
import profile.vo.FileVO;
import profile.vo.PaginationInfo;

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
@RequiredArgsConstructor
@Service
public class FileServiceImpl implements FileService {
	
	
	private final FileDAO dao;

	@Override
	public List<FileVO> retrieveFileList(PaginationInfo<FileVO> paging) {
		
		int totalRecord = dao.selectTotalRecord(paging);
		paging.setTotalRecord(totalRecord);
		List<FileVO> dataList = dao.selectFileList(paging);
		paging.setDataList(dataList);
		return dataList;
	}

	@Override
	public FileVO retrieveFile(FileVO file) {
		// TODO Auto-generated method stub
		return dao.selectFile(file);
	}

	@Override
	public ServiceResult removeFile(FileVO fVO) {
		ServiceResult result = null;
		int rowcnt = dao.deleteFile(fVO);
		if(rowcnt >=1){
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAIL;
		}
		return result;
	}

}

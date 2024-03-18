package profile.file.service;

import java.util.List;

import profile.common.ServiceResult;
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
public interface FileService {

	/**
	 * 메서드 설명 파일목록조회
	 * @Method Name  	: retrieveFileList
	 * @date   			: 2024. 3. 18.
	 * @author   		: boyoung
	 * @version     	: 1.0
	 * ----------------------------------------
	 * @param paging
	 */
	List<FileVO> retrieveFileList(PaginationInfo<FileVO> paging);

	FileVO retrieveFile(FileVO file);

	ServiceResult removeFile(FileVO fVO);

}

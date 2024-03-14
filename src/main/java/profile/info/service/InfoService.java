package profile.info.service;

import java.util.List;

import profile.common.ServiceResult;
import profile.vo.CodeVO;
import profile.vo.FileVO;

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
public interface InfoService {

	
	FileVO selectProfileImg();

	ServiceResult addImg(FileVO fVO);

	List<CodeVO>  getChart();

}

package profile.gallery.service;

import java.util.List;

import profile.common.ServiceResult;
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
public interface GalleryService {

	/**
	* 메서드 설명 ->갤러리 파일 업로드
	* @Method Name  	: upload
	* @date   			: 2024. 3. 12.
	* @author   		: boyoung
	* @version     	: 1.0
	* ----------------------------------------
	* @return
	*/
	ServiceResult upload(GalleryVO gVO);

	/**
	* 메서드 설명 ->갤러리 파일 리스트
	* @Method Name  	: retrieveGalleryList
	* @date   			: 2024. 3. 12.
	* @author   		: boyoung
	* @version     	: 1.0
	* ----------------------------------------
	* @return
	*/
	List<FileVO> retrieveGalleryList();

	FileVO selectFileName(String fileCode);
	

}

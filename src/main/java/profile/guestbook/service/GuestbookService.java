package profile.guestbook.service;

import java.util.List;

import profile.common.ServiceResult;
import profile.vo.GuestbookVO;

/**
 * 프로그램 설명
 * @date        : 2024. 3. 8.
 * @author      : boyoung
 * @version	: 1.0
 * <PRE>
 * ----------------------------
 * 개정이력
 * 2024. 3. 8. boyoung : 최초작성
 * </PRE>
 */


public interface GuestbookService {

	public ServiceResult createGB(GuestbookVO gbVO);

	public ServiceResult modifyGB(GuestbookVO gbVO);

	public ServiceResult removeGB(GuestbookVO gbVO);

	public List<GuestbookVO> retrieveGB(GuestbookVO gbVO);

}

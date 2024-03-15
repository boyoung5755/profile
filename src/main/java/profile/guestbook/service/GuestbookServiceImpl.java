package profile.guestbook.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import profile.common.ServiceResult;
import profile.guestbook.dao.GuesctbookDAO;
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

@Service
@RequiredArgsConstructor
public class GuestbookServiceImpl implements GuestbookService{
	
	
	private final GuesctbookDAO dao;

	@Override
	public ServiceResult createGB(GuestbookVO gbVO) {
		// TODO Auto-generated method stub
		return dao.insertGB(gbVO);
	}

	@Override
	public ServiceResult modifyGB(GuestbookVO gbVO) {
		// TODO Auto-generated method stub
		return dao.updateGB(gbVO);
	}

	@Override
	public ServiceResult removeGB(GuestbookVO gbVO) {
		// TODO Auto-generated method stub
		return dao.deleteGB(gbVO);
	}


	@Override
	public List<GuestbookVO> retrieveGB() {
		// TODO Auto-generated method stub
		return dao.selectList();
	}

}

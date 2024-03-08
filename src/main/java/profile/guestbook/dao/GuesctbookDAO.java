package profile.guestbook.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

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

@Mapper
public interface GuesctbookDAO {

	public ServiceResult insertGB(GuestbookVO gbVO);

	public ServiceResult updateGB(GuestbookVO gbVO);

	public ServiceResult deleteGB(GuestbookVO gbVO);

	public List<GuestbookVO> selectList();

}

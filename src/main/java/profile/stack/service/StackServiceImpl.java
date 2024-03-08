package profile.stack.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import profile.stack.dao.StackDAO;
import profile.vo.StackVO;

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
public class StackServiceImpl  implements StackService{
	
	private final StackDAO dao;

	@Override
	public List<StackVO> testselect() {
		return dao.testselect();
	}

}

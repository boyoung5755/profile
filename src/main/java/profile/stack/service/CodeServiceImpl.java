package profile.stack.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import profile.stack.dao.CodeDAO;
import profile.vo.CodeVO;

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

	@Override
	public List<CodeVO> retrieveCodeTitleList(int stackNo) {
		return dao.selectCodeTitleList(stackNo);
	}

}

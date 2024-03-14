package profile.stack.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import profile.vo.CodeVO;
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

@Mapper
public interface StackDAO {

	public List<StackVO> testselect();

	public List<StackVO> selectStackList();

	public List<CodeVO> selectStackCodeList();

}

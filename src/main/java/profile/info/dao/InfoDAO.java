package profile.info.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

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
@Mapper
public interface InfoDAO {

	FileVO selectProfileImg();

	void changeShow();

	List<CodeVO> getChart();


}

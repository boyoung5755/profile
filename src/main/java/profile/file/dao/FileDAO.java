package profile.file.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import profile.vo.FileVO;
import profile.vo.PaginationInfo;

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

@Mapper
public interface FileDAO {

	public int insertProfileFile(FileVO atch);

	public FileVO selectFileName(String fileCode);

	public int selectTotalRecord(PaginationInfo<FileVO> paging);

	public List<FileVO> selectFileList(PaginationInfo<FileVO> paging);

	public FileVO selectFile(FileVO file);

	public int deleteFile(FileVO fVO);

}

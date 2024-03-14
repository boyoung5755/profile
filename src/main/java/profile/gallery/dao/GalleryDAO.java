package profile.gallery.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

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

@Mapper
public interface GalleryDAO {

	int insertGallery(GalleryVO gVO);

	List<FileVO> selectGalleryList();


}

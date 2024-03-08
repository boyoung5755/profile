package profile.vo;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

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

@Data
@EqualsAndHashCode(of="imgNo")
public class GalleryVO implements Serializable {
	
	

	private static final long serialVersionUID = 1L;
	
	private int imgNo;
	private String imgRdate;
	private String imgDel;
	
	
	private String ckImage;   //ck에터로 저장한 이미지
	

}

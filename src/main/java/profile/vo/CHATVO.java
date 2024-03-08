package profile.vo;

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
@EqualsAndHashCode(of="chatNo")
public class CHATVO {
	
	private int chatNo;
	private String chatRdate;
	private String chatNick;
	private String chatContent;
	

}

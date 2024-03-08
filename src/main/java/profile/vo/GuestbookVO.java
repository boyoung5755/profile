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
@EqualsAndHashCode(of="gbNo")
public class GuestbookVO {

	private int gbNo;
	private String gbRdate;
	private String gbNick;
	private String gbPw;
	private String gbDel;
	private String gbContent;
}

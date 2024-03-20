package profile.vo;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 프로그램 설명
 * @date        : 2024. 3. 20.
 * @author      : boyoung
 * @version	: 1.0
 * <PRE>
 * ----------------------------
 * 개정이력
 * 2024. 3. 20. boyoung : 최초작성
 * </PRE>
 */

@Data
@EqualsAndHashCode(of="memNo")
public class MemberVO {
	
	private int memNo;
	private String memEmail;
	private String memNickname;
	private String memRole;
	
	private LocalDateTime memRdate;
	

}

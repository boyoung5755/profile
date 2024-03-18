package profile.vo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 프로그램 설명
 * @date        : 2024. 3. 18.
 * @author      : boyoung
 * @version	: 1.0
 * <PRE>
 * ----------------------------
 * 개정이력
 * 2024. 3. 18. boyoung : 최초작성
 * </PRE>
 */



@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchVO implements Serializable{
	private String searchType;
	private String searchWord;
}
package profile.common;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;

/**
 * 프로그램 설명
 * @date        : 2024. 3. 10.
 * @author      : boyoung
 * @version	: 1.0
 * <PRE>
 * ----------------------------
 * 개정이력
 * 2024. 3. 10. boyoung : 최초작성
 * </PRE>
 */

public class Utils {
	
	@Value("#{appInfo.codeFiles}")
	private Resource codeFiles	;

	//성공실패 보내주기
	public Map<String, String> sendResult(ServiceResult result){
		Map<String, String> map = new HashMap<>();
		
		if(result.equals(ServiceResult.OK)) {
			map.put("success","Y");
		}else {
			map.put("success","N");
		}
		return map;
	}
	
	
}

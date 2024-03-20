package profile.kakao.service;

import java.util.HashMap;

import profile.vo.MemberVO;

/**
 * 프로그램 설명
 * @date        : 2024. 3. 19.
 * @author      : boyoung
 * @version	: 1.0
 * <PRE>
 * ----------------------------
 * 개정이력
 * 2024. 3. 19. boyoung : 최초작성
 * </PRE>
 */
public interface KakaoService {

	String getKakaoAccessToken(String code);

	MemberVO getUserInfo(String accessToken);

}

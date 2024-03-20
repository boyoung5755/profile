package profile.kakao.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import profile.member.dao.MemberDAO;
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

@Slf4j
@RequiredArgsConstructor
@Service
public class KakaoServiceImpl implements KakaoService {
	
	
	@Value("#{appInfo.kakaoApiKey}")
	private String kakaoApiKey	;
	@Value("#{appInfo.kakaoRedirectUri}")
	private String kakaoRedirectUri	;
	
	private final MemberDAO dao;
	
	
	

	@Override
	public String getKakaoAccessToken(String code) {
		String accessToken = "";
	    String refreshToken = "";
	    String requestURL = "https://kauth.kakao.com/oauth/token";

	    try {
	        URL url = new URL(requestURL);
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

	        conn.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
	        // setDoOutput()은 OutputStream으로 POST 데이터를 넘겨 주겠다는 옵션이다.
	        // POST 요청을 수행하려면 setDoOutput()을 true로 설정한다.
	        conn.setRequestMethod("POST");
	        conn.setDoOutput(true);

	        // POST 요청에서 필요한 파라미터를 OutputStream을 통해 전송
	        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
	        StringBuilder sb = new StringBuilder();
	        
	        //필수 쿼리 파라미터 세팅
	        sb.append("grant_type=authorization_code");
	        sb.append("&client_id=").append(kakaoApiKey);
	        sb.append("&redirect_uri=").append(kakaoRedirectUri);
	        sb.append("&code=").append(code);
	        sb.append("&client_secret=").append("qge0o5kXOLNFiENBtv72iVDBkbPqPOOx");
	       
	        bufferedWriter.write(sb.toString());
	        bufferedWriter.flush();

	        int responseCode = conn.getResponseCode();
	        log.info("[KakaoApi.getAccessToken] responseCode = {}", responseCode);

	        // 요청을 통해 얻은 데이터를 InputStreamReader을 통해 읽어 오기
	        BufferedReader br;
	        if (responseCode >= 200 && responseCode <= 300) {
	            br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        } else {
	            br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
	        }
 	        
	        String line = "";
	        StringBuilder responseSb = new StringBuilder();
	        while((line = br.readLine()) != null){
	            responseSb.append(line);
	        }
	        String result = responseSb.toString();
	        log.info("responseBody = {}", result);

	        JsonParser parser = new JsonParser();
	        JsonElement element = parser.parse(result);
	        accessToken = element.getAsJsonObject().get("access_token").getAsString();
	        refreshToken = element.getAsJsonObject().get("refresh_token").getAsString();

	        br.close();
	        bufferedWriter.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    return accessToken;

	}

	@Override
	public MemberVO getUserInfo(String accessToken) {
		MemberVO mVO = new MemberVO();
		 HashMap<String, Object> userInfo = new HashMap<>();
		    String postURL = "https://kapi.kakao.com/v2/user/me";

		    try {
		        URL url = new URL(postURL);
		        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		        conn.setRequestMethod("POST");

		        conn.setRequestProperty("Authorization", "Bearer " + accessToken);
		        conn.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

		        int responseCode = conn.getResponseCode();
		        log.info("[KakaoApi.getUserInfo] responseCode : {}",  responseCode);

		        BufferedReader br;
		        if (responseCode >= 200 && responseCode <= 300) {
		            br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		        } else {
		            br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		        }

		        String line = "";
		        StringBuilder responseSb = new StringBuilder();
		        while((line = br.readLine()) != null){
		            responseSb.append(line);
		        }
		        String result = responseSb.toString();
		        log.info("로그인정보 responseBody = {}", result);

		        JsonParser parser = new JsonParser();
		        JsonElement element = parser.parse(result);

		        JsonObject properties = element.getAsJsonObject().get("properties").getAsJsonObject();
		        JsonObject kakaoAccount = element.getAsJsonObject().get("kakao_account").getAsJsonObject();

		        String nickname = properties.getAsJsonObject().get("nickname").getAsString();
		        String email = kakaoAccount.getAsJsonObject().get("email").getAsString();
		        
		        log.info("닉네임>>>>>>>>>>>>>>>>"+nickname);
		        log.info("닉네임>>>>>>>>>>>>>>>>"+email);
		        
		        userInfo.put("nickname", nickname);
		        userInfo.put("email", email);
		        
		        mVO = checkMember(userInfo);
		        
		        br.close();

		    }catch (Exception e){
		        e.printStackTrace();
		    }
		    return mVO;
	}
	
	
	
	//카카오 로그인한 사람이 가입된 사람인지 role이 무엇인지 확인하는 메서드
	public MemberVO checkMember(HashMap<String, Object> userInfo) {
		
		MemberVO mVO = new MemberVO();
		//디비에서 찾기
		mVO = dao.selectMember((String) userInfo.get("email"));
		
		return mVO;
	}
	

}

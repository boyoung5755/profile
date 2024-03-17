package profile.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

/**
 * 프로그램 설명
 * @date        : 2024. 3. 17.
 * @author      : boyoung
 * @version	: 1.0
 * <PRE>
 * ----------------------------
 * 개정이력
 * 2024. 3. 17. boyoung : 최초작성
 * </PRE>
 */

@Slf4j
public class CustomIntercepter implements HandlerInterceptor{

	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		log.info("□□□□□□□□□□□□□□□□□□□□□□□□□□□ preHandle");
        // 컨트롤러 실행 전에 수행할 작업
		
		String role = (String)request.getSession().getAttribute("role");
		
		if("admin".equals(role)) {
			log.info("관리자로 로그인");
			return true;
		}else{
	
			return false;
		}
        
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // 컨트롤러 실행 후에 수행할 작업
    	log.info("□□□□□□□□□□□□□□□□□□□□□□□□□□□ postHandle");
    	
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 응답 완료 후에 수행할 작업
    }
}

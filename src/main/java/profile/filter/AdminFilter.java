package profile.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;

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

@Log4j2
@Component
public class AdminFilter implements Filter{

	@Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("AdminFilter가 생성 됩니다.");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
    	HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
    	HttpSession session = httpRequest.getSession();
    	
    	String path = httpRequest.getRequestURI();
        
        //"/login" , "/"가 아닐때 
        
        if(path.startsWith("/stack/form") || path.startsWith("/stack/edit")) {
        	log.info("==========Login 필터 시작!==========");
        	
        	log.info("====현재 URI======"+ path);
        	
        	
        	String loginName = (String) session.getAttribute("role");
        	
        	//세션에 안담겨있을때
        	if (loginName == null) {
        	   HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        	   httpResponse.sendError(HttpServletResponse.SC_NOT_FOUND, "로그인 후 이용가능합니다.");
        	}
        	log.info("==========Login 필터 종료!==========");
        }
        
        filterChain.doFilter(servletRequest, servletResponse);
        
    }
    @Override
    public void destroy() {
        log.info("AdminFilter가 사라집니다.");
    }
    
}

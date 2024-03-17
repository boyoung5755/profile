package profile.filter;

import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

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


public class WebConfig implements WebMvcConfigurer{
	
	@Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new CustomIntercepter())
                .addPathPatterns("/**");  // 인터셉터를 적용할 URL 패턴을 지정합니다.
	}

}

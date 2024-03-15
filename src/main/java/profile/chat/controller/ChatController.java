package profile.chat.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import profile.chat.Greeting;
import profile.chat.HelloMessage;
import profile.chat.service.ChatService;
import profile.common.Utils;
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

@Slf4j
@Controller
@RequiredArgsConstructor
public class ChatController {
	

	
    @MessageMapping("/enter")
    @SendTo("/topic/greetings")
    public Greeting enter(HelloMessage message, StompHeaderAccessor session) throws Exception {
    	log.info("입장>>>>>>>>>>>>>>>>>"+session.getSessionAttributes().get("name"));
        return new Greeting(HtmlUtils.htmlEscape(session.getSessionAttributes().get("name") + "님께서 입장하셨습니다!"));
    }
    @MessageMapping("/exit")
    @SendTo("/topic/greetings")
    public Greeting exit(HelloMessage message, StompHeaderAccessor session) throws Exception {
        return new Greeting(HtmlUtils.htmlEscape(session.getSessionAttributes().get("name") + "님께서 퇴장하셨습니다!"));
    }
    @MessageMapping("/chat")
    @SendTo("/topic/greetings")
    public Greeting chat(HelloMessage message, StompHeaderAccessor session) throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date now = new Date();

        String currentTime = format.format(now);

        System.out.println(currentTime);
        log.info("대화>>>>>>>>>>>>>>>>>"+session.getSessionAttributes().get("chat") + " : "+message.getChat());
        return new Greeting(HtmlUtils.htmlEscape(session.getSessionAttributes().get("chat") + " : "+message.getChat()+"["+currentTime+"]"));
    }
    

}

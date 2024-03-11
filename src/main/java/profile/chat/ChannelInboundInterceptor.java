package profile.chat;

import java.util.Map;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Component;

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

@Component
public class ChannelInboundInterceptor implements ChannelInterceptor {
    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor header = StompHeaderAccessor.wrap(message);
        if (StompCommand.CONNECT.equals(header.getCommand())) {
            //connect라면 name값을 꺼내서 sessionAttributes에 넣기.
            Map<String, Object> attributes = header.getSessionAttributes();
            attributes.put("name", header.getFirstNativeHeader("name"));
            header.setSessionAttributes(attributes);
        }
        return message;
    }
}
//이제 처음 연결을 맺을 때 name 값을 sessionAttributes에 넣는다.
package websocket;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Mono;
import tools.Console;

/**
 * 测试流程:
 * ①: 登录https://www.websocket.org/echo.html
 * ②: ws://127.0.0.1:8080/echo 发起连接
 * @author liuxin
 * @version Id: EchoHandler.java, v 0.1 2018/6/21 下午4:45
 */
@Component
public class EchoHandler implements WebSocketHandler {
    @Override
    public Mono<Void> handle(final WebSocketSession session) {
        return session.send(
                session.receive()
                        .map(msg -> {
                            String payloadAsText = msg.getPayloadAsText();
                            Console.log(payloadAsText);
                            return session.textMessage("ECHO -> " + payloadAsText);
                        }));
    }
}

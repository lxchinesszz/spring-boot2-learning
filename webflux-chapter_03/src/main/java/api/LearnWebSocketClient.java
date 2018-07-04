package api;

import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.client.ReactorNettyWebSocketClient;
import org.springframework.web.reactive.socket.client.WebSocketClient;
import reactor.core.publisher.Flux;
import tools.Console;

import java.net.URI;
import java.time.Duration;

/**
 * @author liuxin
 * @version Id: LearnWebSocketClient.java, v 0.1 2018/6/21 下午5:04
 */
public class LearnWebSocketClient {
    public static void main(final String[] args) {
        final WebSocketClient client = new ReactorNettyWebSocketClient();
        client.execute(URI.create("ws://localhost:8080/echo"), session ->
                session.send(Flux.just(session.textMessage("Hello")))
                        //接受消息
                        .thenMany(session.receive().take(1).map(WebSocketMessage::getPayloadAsText))
                        //消费消息
                        .doOnNext(Console::log)
                        .then())
                .block(Duration.ofMillis(5000));
    }
}

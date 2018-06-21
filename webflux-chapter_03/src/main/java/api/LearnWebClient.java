package api;

import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.ExchangeFilterFunctions;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import tools.Console;

/**
 * 通过与RestTemplate比较， WebClient是：
 * <p>
 * 非阻塞，反应性，并支持更高的并发性和更少的硬件资源。
 * <p>
 * 提供了一个可以利用Java 8 lambda的功能性API。
 * <p>
 * 支持同步和异步场景。
 * <p>
 * 支持从服务器上传或下传。
 * <p>
 * 这RestTemplate不适合在非阻塞应用程序中使用，
 * 因此Spring WebFlux应用程序应始终使用WebClient。
 * 的WebClient也应在Spring MVC优选的，在大多数高并发场景，和用于构成远程，相互依存的调用序列。
 *
 * @author liuxin
 * @version Id: LearnWebClient.java, v 0.1 2018/6/21 下午12:44
 */
public class LearnWebClient {
    public static void main(String[] args) {
        /**
         * 创建一个简单的方法WebClient是通过静态工厂方法create()和 create(String)所有请求的基本URL。
         * 我们也可以使用WebClient.builder() 访问更多选项。
         */
        WebClient client = WebClient.create("https://www.baidu.com/");
        /**
         * 该retrieve()方法是获取响应主体并对其进行解码的最简单方法
         */
        Mono<String> stringMono = client.get().retrieve().bodyToMono(String.class);
        Console.log(stringMono.block());

        MultiValueMap<String, String> formData =new LinkedMultiValueMap<>();
        client.post().syncBody(formData).retrieve()
                .bodyToMono(Void.class);

    }

    private static void webClient()throws Exception{
        /**
         * 创建一个简单的方法WebClient是通过静态工厂方法create()和 create(String)所有请求的基本URL。
         * 我们也可以使用WebClient.builder() 访问更多选项。
         */
        WebClient webClient0 = WebClient.create("https://www.baidu.com/");

        /**
         * 创建一个带ssl
         */
        SslContext sslContext = SslContextBuilder.forClient().build();

        ClientHttpConnector connector = new ReactorClientHttpConnector(
                builder -> builder.sslContext(sslContext));

        WebClient webClient1 = WebClient.builder()
                .clientConnector(connector)
                .build();


        /**
         * 定制用于编码和解码HTTP消息的HTTP编解码器：
         */
        ExchangeStrategies strategies = ExchangeStrategies.builder()
                .codecs(configurer -> {
                    //...
                })
                .build();

        WebClient webClient2 = WebClient.builder()
                .exchangeStrategies(strategies)
                .build();


        /**
         * 构建一个带有用户认证的客户端
         */
        WebClient webClient3 = WebClient.builder()
                .filter(ExchangeFilterFunctions.basicAuthentication("user", "pwd"))
                .build();
    }
}

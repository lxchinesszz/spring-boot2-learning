package api;

import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.*;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;
import tools.Console;

import java.net.InetSocketAddress;

/**
 * 使用WebFilter编写的拦截器会广泛影响到所有端点,
 * 并覆盖了用函数样式编写的WebFlux端点， 以及使用注释样式编写的端点。
 * @author liuxin
 * @version Id: LearnWebFilter.java, v 0.1 2018/6/21 上午10:40
 */
public class LearnWebFilter {
    public static final String host = "127.0.0.1";
    public static final int port = 8081;
    private static final String URL = "/03/";

    /**
     * 测试过滤器
     * @param args
     */
    public static void main(String[] args) {
        webFilter();
    }


    /**
     * 测试过滤器配置成功
     */
    private static void webFilter(){
        RouterFunction<ServerResponse> routerFunction = RouterFunctions.route(RequestPredicates.GET(URL), request ->  ServerResponse.ok().body(BodyInserters.fromObject("hello web filter")));
        /**
         * 处理策略
         * webFilter : 过滤器
         * exceptionHandler : 异常拦截
         */
        HandlerStrategies handlerStrategies = HandlerStrategies.builder().exceptionHandler(new LearnRouterFunction.CustomerExceptionHandler())
                .webFilter(new CustomerWebFilter()).build();

        /**
         * 定义处理器
         */
        HttpHandler httpHandler = RouterFunctions.toHttpHandler(routerFunction, handlerStrategies);

        LearnServer.web_netty(host,port,httpHandler);
    }

    /**
     * 自定义过滤器
     */
    public static class CustomerWebFilter implements WebFilter {
        @Override
        public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
            InetSocketAddress remoteAddress = exchange.getRequest().getRemoteAddress();
            Console.warn(remoteAddress);
            Console.log("filter success");
            return chain.filter(exchange);
        }
    }
}

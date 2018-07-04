package api;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.*;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebExceptionHandler;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;
import tools.Console;

import java.net.InetSocketAddress;

/**
 * RouterFunctions用于将请求路由和处理函数HandlerFunction,绑定到一起
 * 就像其名字一样Router路由+Functions函数方法
 *
 * 通常，你不要自己写路由器功能，
 * 而是使用 RouterFunctions.route(RequestPredicate, HandlerFunction)。
 * 则请求被路由到给定的地址HandlerFunction，否则不执行路由选择,并且这将转化为404（未找到）响应。
 *
 * @author liuxin
 * @version Id: LearnRouterFunction.java, v 0.1 2018/6/21 上午10:12
 */
public class LearnRouterFunction {
    private static final String URL = "/03/{name}";

    public static void main(String[] args) {
        /**
         * 定义一个指定为POST请求的路由
         */
        RouterFunctions.route(RequestPredicates.POST(URL), new HandlerFunction<ServerResponse>() {
            @Override
            public Mono<ServerResponse> handle(ServerRequest request) {
                request.cookies();
                request.attributes();
                request.formData();
                request.headers();
                request.path();
                request.pathVariable("name");
                return ServerResponse.ok().body(BodyInserters.fromObject("hello netty"));
            }
        });

        /**
         * 定义两个路由
         * 1: get请求的路由函数
         * 2: post请求的路由函数
         */
        RouterFunctions.route(RequestPredicates.POST(URL), request -> null).and(
                RouterFunctions.route(RequestPredicates.GET(URL), request -> null)
        );


        /**
         * 定义一个只能使用application/json;charset=utf-8访问的POST请求
         */
        RouterFunctions.route(RequestPredicates.POST(URL)
                        .and(RequestPredicates.accept(MediaType.APPLICATION_JSON_UTF8))
                , request -> null);


        RouterFunction<ServerResponse> routerFunction = RouterFunctions.route(RequestPredicates.POST(URL), request -> null);


        /**
         * 处理策略
         * webFilter : 过滤器
         * exceptionHandler : 异常拦截
         */
        HandlerStrategies handlerStrategies = HandlerStrategies.builder().exceptionHandler(new CustomerExceptionHandler())
                .webFilter(new CustomerWebFilter()).build();


        /**
         * 定义处理器
         */
        RouterFunctions.toHttpHandler(routerFunction, handlerStrategies);




    }

    /**
     * 自定义异常处理器
     */
    public static class CustomerExceptionHandler implements WebExceptionHandler {
        @Override
        public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
            if (ex instanceof IllegalArgumentException) {
                /**
                 * 如果有参数异常,返回状态
                 * 500, "Internal Server Error"
                 */
                exchange.getResponse().setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return Mono.empty();
        }
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

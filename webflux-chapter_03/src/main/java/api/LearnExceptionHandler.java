package api;

import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.*;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebExceptionHandler;
import reactor.core.publisher.Mono;

/**
 * 意如其名,是对异常的处理器,拦截所有异常并作出相应
 * 于此想呼应的是,我们在传统web-mvc中使用
 *
 * @version Id: LearnExceptionHandler.java, v 0.1 2018/6/21 下午12:28
 * @<code>
 * @ControllerAdvice public class ResponseException extends ResponseEntityExceptionHandler {
 * <p>
 * private static final Logger logger = LoggerFactory.getLogger(ResponseException.class);
 * @ExceptionHandler(Exception.class)
 * @ResponseBody ResponseEntity handleControllerException(Exception ex) {
 * ex.printStackTrace();
 * logger.error("exception message:{}", ex.getMessage());
 * return new ResponseEntity(new CustoError(10000, "服务器异常"), HttpStatus.BAD_REQUEST);
 * }
 * }
 * </code>
 * <p>
 * * @author liuxin
 */
public class LearnExceptionHandler {
    public static final String host = "127.0.0.1";
    public static final int port = 8081;
    private static final String URL = "/03/";

    public static void main(String[] args) {
        webException();
    }

    private static void webException() {
        RouterFunction<ServerResponse> routerFunction = RouterFunctions.route(RequestPredicates.GET(URL), request ->
                {
                    Mono<ServerResponse> body = ServerResponse.ok().body(BodyInserters.fromObject("hello web exception"));
                    try {
                        int a = 1 / 0;
                    } catch (Exception e) {
                        throw new IllegalArgumentException("test");
                    }
                    return body;
                }
        );
        /**
         * 处理策略
         * webFilter : 过滤器
         * exceptionHandler : 异常拦截
         */
        HandlerStrategies handlerStrategies = HandlerStrategies.builder().exceptionHandler(new CustomerExceptionHandler())
                .build();

        /**
         * 定义处理器
         */
        HttpHandler httpHandler = RouterFunctions.toHttpHandler(routerFunction, handlerStrategies);

        LearnServer.web_netty(host, port, httpHandler);
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
}

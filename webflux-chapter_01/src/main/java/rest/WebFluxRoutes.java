package rest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * @author liuxin
 * @version Id: WebFluxRoutes.java, v 0.1 2018/6/20 下午11:49
 */
@Configuration
public class WebFluxRoutes {
    public static final String RESULT = "hello web-flux";

    @Bean(name = "customer-webflux-route-01")
    public RouterFunction<ServerResponse> webFluxGet() {
        Mono<String> date = Mono.just(RESULT);
        return RouterFunctions.route(RequestPredicates.path("/01/webflux/get"),
                request -> ServerResponse.ok().body(date, String.class));
    }
}

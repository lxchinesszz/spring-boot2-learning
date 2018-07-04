package rest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.Optional;

/**
 * @author liuxin
 * @version Id: WebFluxRoutes.java, v 0.1 2018/6/20 下午11:49
 */
@Configuration
public class WebFluxRoutes {

    @Bean(name = "customer-webflux-route-02-01")
    public RouterFunction<ServerResponse> webFluxGet() {
        return RouterFunctions.route(RequestPredicates.path("/02/webflux/get"),
                request ->
                {
                    Optional<String> name = request.queryParam("name");
                    Optional<String> age = request.queryParam("age");
                    Mono<String> date = Mono.just(name.orElse("default") + age.orElse("default age"));
                    return ServerResponse.ok().body(date, String.class);
                });
    }

    @Bean(name = "customer-webflux-route-02-02")
    public RouterFunction<ServerResponse> webFluxPost() {
        return RouterFunctions.route(RequestPredicates.path("/02/webflux/post"),
                request ->
                {
//                    Mono<MultiValueMap<String, String>> multiValueMapMono = request.formData();
//request
                    return ServerResponse.ok().body(Mono.just(""), String.class);
                });
    }


}

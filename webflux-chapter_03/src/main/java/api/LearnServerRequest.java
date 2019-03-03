package api;

import app.entities.Person;
import org.springframework.http.codec.multipart.Part;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyExtractors;
import org.springframework.web.reactive.function.server.ServerRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author liuxin
 * @version Id: LearnServerRequest.java, v 0.1 2018/6/21 上午9:53
 */
public class LearnServerRequest {
    public static void main(String[] args) {
        ServerRequest request = null;
        Mono<String> var1 = request.bodyToMono(String.class);


        Flux<Person> var2 = request.bodyToFlux(Person.class);


        Mono<String> var3 = request.body(BodyExtractors.toMono(String.class));
        Flux<Person> var4 = request.body(BodyExtractors.toFlux(Person.class));

        /**
         * 访问表单数据
         */
        Mono<MultiValueMap<String, String>> var5 = request.body(BodyExtractors.toFormData());


        Mono<MultiValueMap<String, Part>> body = request.body(BodyExtractors.toMultipartData());


        Flux<Part> parts = request.body(BodyExtractors.toParts());
    }
}

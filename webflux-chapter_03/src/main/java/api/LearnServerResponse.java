package api;

import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseCookie;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerResponse;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author liuxin
 * @version Id: LearnServerResponse.java, v 0.1 2018/6/21 上午9:55
 */
public class LearnServerResponse {
    public static void main(String[] args) {
        String result = "hello world";

        /**
         * 生成cookie
         */
        ResponseCookie cookie = ResponseCookie.from("id", UUID.randomUUID().toString())
                .maxAge(TimeUnit.MINUTES.toMillis(5)).build();

        /**
         * body:
         * contentType:
         * contentLength:
         * switchIfEmpty:
         */
        ServerResponse.ok().contentType(MediaType.APPLICATION_JSON_UTF8).contentLength(result.length())
                .cookie(cookie).allow(HttpMethod.GET,HttpMethod.POST).header("head-name","head-value")
                .body(BodyInserters.fromObject(result)).switchIfEmpty(ServerResponse.notFound().build());
    }
}

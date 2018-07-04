package rest;

import io.netty.util.internal.ThreadLocalRandom;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuples;

import java.time.Duration;


/**
 * @author liuxin
 * @version Id: WebFluxController.java, v 0.1 2018/6/20 下午11:20
 */
@RestController
public class WebMvcController {

    public static final String RESULT = "hello mvc";

    @GetMapping(value = "/02/mvc/get")
    public String mvcGet(@RequestParam(value = "name") String n, int age) {
        return n+String.valueOf(age);
    }

    @PostMapping(value = "/02/mvc/post")
    public String mvcPost(String name, int age) {
        return name+String.valueOf(age);
    }


    @GetMapping("/randomNumbers")
    public Flux<ServerSentEvent<Integer>> randomNumbers() {
        return Flux.interval(Duration.ofSeconds(1))
                .map(seq -> Tuples.of(seq, ThreadLocalRandom.current().nextInt()))
                .map(data -> ServerSentEvent.<Integer>builder()
                        .event("random")
                        .id(Long.toString(data.getT1()))
                        .data(data.getT2())
                        .build());
    }
}

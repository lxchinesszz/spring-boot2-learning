package api;

import reactor.core.publisher.Mono;
import tools.Console;

import java.time.Duration;


/**
 * 参考文档：https://projectreactor.io/docs/core/release/reference/#flux
 * 我们可以把Mono理解为一个结果它对应的数据是 1
 * Mono和Flux的设计类似于RxJava
 * 同样是基于事件模式或者说是订阅者模式
 * 可以生产一个消息,然后常见多个消费者
 *
 * @author liuxin
 * @version Id: Learn.java, v 0.1 2018/6/21 上午9:36
 */
public class LearnMono {

    public static void main(String[] args) {
        //① 生成一个消息
        Mono.from(Mono.just("hello"))
                // ② 第一个消费
                .doOnNext(Console::normal)
                // ③ 第二个消费
                .doOnNext(Console::unnormal).block();



        Mono.from(Mono.just("hello"))
                .doOnNext(msg -> Console.customerNormal("正常①", msg))
                .doOnNext(msg -> {
                    try {
                        int a = 1 / 0;
                    } catch (Exception e) {
                        throw new RuntimeException("模拟一个异常");
                    }
                    Console.customerNormal("正常②", msg);
                })
                .doOnNext(msg -> Console.customerNormal("正常③", msg))
                .doOnError(msg -> Console.customerunNormal("进入消费异常逻辑",msg.getMessage()))
                .block(Duration.ofMinutes(10));
    }
}

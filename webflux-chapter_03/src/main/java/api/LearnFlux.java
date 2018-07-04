package api;

import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;

/**
 * 参考文档：https://projectreactor.io/docs/core/release/reference/#flux
 * Flux 相当于一个 RxJava Observable 观察者
 * 观察者可以把生产者的消息Publisher,推送给消费者subscribe
 * <p>
 * 我们可以把Flux理解为集合的它对应的数据是{0，N}
 *
 * @author liuxin
 * @version Id: LearnFlux.java, v 0.1 2018/6/21 上午9:37
 */
public class LearnFlux {
    public static void main(String[] args) {
    learnJust();
        Flux.range(1, 10).subscribe(System.out::println);
    }

    private static void learnJust(){
        Flux.just(1,2,3,4).map(n->"@"+n).subscribe(System.out::println);
    }
    private static void API(){
        /**
         * 两个生产者
         * 一个订阅者
         *
         * 以下是四中创建的API方法
         * 为什么 Nothing Happens Until You subscribe()
         * 因为实际上在我们调用 subscribe() 所有的方法都只是在申明对象。
         * 只有在 subscribe 之后才能出发 onSubscribe 调用。
         */
        Flux.just("1", "2", "3").subscribe(System.out::println);

        Flux.fromArray(new Integer[]{1, 2, 3}).subscribe(System.out::println);

        Flux.fromArray(new String[]{"1", "2", "3"}).subscribe(System.out::println);

        Flux.fromStream(Arrays.asList("1", "2", "3").stream()).subscribe(System.out::println);

        Flux.fromIterable(Arrays.asList("1", "2", "3")).subscribe(System.out::println);

        /**
         * 空的
         */
        Flux.empty().subscribe(System.out::println);

        /**
         * 创建一个循环
         */
        Flux.range(1, 10).subscribe(System.out::println);

        /**
         *
         */
        Flux.interval(Duration.of(10, ChronoUnit.SECONDS)).subscribe(System.out::println);


        Flux<Integer> ints = Flux.range(2, 3);
        ints.subscribe(i -> System.out.println(i));
    }
}

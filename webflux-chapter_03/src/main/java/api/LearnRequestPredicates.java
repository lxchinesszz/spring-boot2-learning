package api;

import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicate;
import org.springframework.web.reactive.function.server.RequestPredicates;

/**
 * 请求谓语
 * 我们可以理解为定义路由URL和请求方式的工具类
 * 比较简单,支持所有的方式请求,可以根据下面的例子，举一反三
 *
 * @author liuxin
 * @version Id: LearnRequestPredicates.java, v 0.1 2018/6/21 上午10:12
 */
public class LearnRequestPredicates {
    private static final String URL = "/03/";

    public static void main(String[] args) {

        /**
         * post 请求方式一
         */
        RequestPredicates.method(HttpMethod.POST).and(RequestPredicates.path(URL));

        /**
         * post 请求方式二
         */
        RequestPredicates.POST(URL);

        /**
         * 两个都匹配
         */
        RequestPredicates.POST(URL).and(RequestPredicates.method(HttpMethod.POST).and(RequestPredicates.path(URL)));

        /**
         * 匹配任意一个
         */
        RequestPredicates.GET(URL).or(RequestPredicates.method(HttpMethod.POST).and(RequestPredicates.path(URL)));

        /**
         * 匹配一个仅能使用application/json;charset=utf-8访问的POST请求
         */
        RequestPredicates.POST(URL).and(RequestPredicates.accept(MediaType.APPLICATION_JSON_UTF8));


    }
}

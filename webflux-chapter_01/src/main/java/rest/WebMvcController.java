package rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author liuxin
 * @version Id: WebFluxController.java, v 0.1 2018/6/20 下午11:20
 */
@RestController
public class WebMvcController {

    public static final String RESULT = "hello mvc";

    @GetMapping(value = "/01/mvc/get")
    public String mvcGet() {
        return RESULT;
    }
}

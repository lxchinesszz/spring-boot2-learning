package rest.service;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


/**
 * @author liuxin
 * @version Id: UserService.java, v 0.1 2019-03-03 12:29
 */
@RestController
public class UserService {

    @GetMapping(path = "/get")
    public String getUser(@RequestParam(name = "name", defaultValue = "Spring Boot") String text) {
        return "hello " + text;
    }

    @GetMapping(path = "/get2", params = "text!=Spring")
    public String getUser2(String text) {
        return "hello " + text;
    }


    @PostMapping(path = "/get3", headers = "name=admin")
    public String getUser3(String text) {
        return "hello " + text;
    }

    @PostMapping(path = "/get4", headers = "name=admin", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String getUser4(String text) {
        return "hello " + text;
    }

    /**
     * 指定客户端到服务端的请求数据类型
     *
     * @param text
     * @return
     */
    @PostMapping(path = "/get5", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getUser5(String text) {
        return "hello " + text;
    }

    /**
     * 指定服务端响应客户端的数据类型
     *
     * @param text
     * @return
     */
    @PostMapping(path = "/get6", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getUser6(String text) {
        return text;
    }



    @RequestMapping(path = "/get7",method = RequestMethod.POST)
    @PostMapping(path = "/get7")
    public String getUse7(String text) {
        return "hello " + text;
    }


}

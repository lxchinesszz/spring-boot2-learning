package web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liuxin
 * @version Id: RestController.java, v 0.1 2019-03-06 10:44
 */
@RestController
public class RestWebController {

  @GetMapping("/get")
  public String get() {
    return "hello Spring Boot2";
  }
}

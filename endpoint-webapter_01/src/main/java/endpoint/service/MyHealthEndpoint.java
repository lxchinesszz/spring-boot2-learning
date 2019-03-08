package endpoint.service;

import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.boot.actuate.endpoint.web.annotation.WebEndpoint;
import org.springframework.stereotype.Component;


/**
 * 自定义端点
 * @author liuxin
 * @version Id: UserService.java, v 0.1 2019-03-08 10:58
 */
@Component
@WebEndpoint(id = "/sessions")
public class MyHealthEndpoint {

  @ReadOperation(produces = "application/json;charset=utf8")
  public Info get(@Selector String name) {
    return new Info(name, "23");
  }
}

package app.service;

import tools.Console;

/**
 * @author liuxin
 * @version Id: ZooService.java, v 0.1 2019-03-04 11:08
 */
public class ZooService {


  public void init() {
    Console.log("initMethod执行");
  }

  public void destroy() {
    Console.log("destroyMethod执行");
  }

  public void say(String message) {
    Console.customerNormal(" say", message);
  }
}

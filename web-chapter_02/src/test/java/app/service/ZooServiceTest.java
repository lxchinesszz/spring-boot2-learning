package app.service;

import app.config.ConfigBeans;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * @author liuxin
 * @version Id: ZooServiceTest.java, v 0.1 2019-03-04 13:55
 */
public class ZooServiceTest {

  /**
   * 注解方式
   */
  @Test
  public void annotationTest() {
    AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext();
    app.register(ConfigBeans.class);
    app.refresh();
    ((ZooService) app.getBean("zooService")).say("hello @Configuration");
    app.close();
  }


  @Test
  public void xmlTest() {
    ClassPathXmlApplicationContext xmlApp = new ClassPathXmlApplicationContext("spring-context.xml");
    ((ZooService) xmlApp.getBean("zooService2")).say("hello xml");
    xmlApp.close();
  }

}

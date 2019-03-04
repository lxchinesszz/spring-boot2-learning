package app.service;
import app.config.Config;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author liuxin
 * @version Id: ConfigApplicationTest.java, v 0.1 2019-03-04 14:06
 */
@RunWith(value = SpringRunner.class)
@SpringBootTest
public class ConfigApplicationTest {

  @Autowired
  Config config;


  @Test
  public void configTest(){
    System.out.println(config);
  }


}

package asyn;

import asyn.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.util.concurrent.CompletableFuture;

/**
 * @author liuxin
 * @version Id: AsynApplication.java, v 0.1 2019-03-11 14:58
 */
@SpringBootApplication
public class AsynApplication {

  private static final Logger logger = LoggerFactory.getLogger(AsynApplication.class);


  @Component
  private class RunnerTest implements ApplicationRunner {

    @Autowired
    UserService us;

    @Override
    public void run(ApplicationArguments args) throws Exception {
      StopWatch sw = new StopWatch("Asyn异步同步耗时统计对比");
      CompletableFuture<String> say1 = us.say1();
      CompletableFuture<String> say2 = us.say2();
      CompletableFuture<String> say3 = us.say3();
      // Wait until they are all done
      CompletableFuture.allOf(say1, say2, say3).join();
      sw.start("异步查询");
      logger.info("--> " + say1.get());
      logger.info("--> " + say2.get());
      logger.info("--> " + say3.get());
      sw.stop();

      sw.start("同步查询");
      logger.info("--> " + us.say1().get());
      logger.info("--> " + us.say2().get());
      logger.info("--> " + us.say3().get());
      sw.stop();
      System.out.println(sw.prettyPrint());
    }
  }

  public static void main(String[] args) {
    SpringApplication.run(AsynApplication.class, args);
  }
}

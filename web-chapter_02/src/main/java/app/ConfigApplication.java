package app;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @author liuxin
 * @version Id: ConfigApplication.java, v 0.1 2019-03-04 11:11
 */
@SpringBootApplication
public class ConfigApplication {

  public static void main(String[] args) {
      new SpringApplicationBuilder().web(WebApplicationType.NONE).sources(ConfigApplication.class).run(args);

  }

}

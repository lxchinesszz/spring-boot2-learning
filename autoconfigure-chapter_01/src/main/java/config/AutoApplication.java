package config;

import config.service.DogService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author liuxin
 * @version Id: AutoApplication.java, v 0.1 2019-03-07 21:51
 */
@SpringBootApplication
public class AutoApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(AutoApplication.class, args);
        DogService bean = run.getBean(DogService.class);
        bean.say("汪汪汪");
        //备用Dog:汪汪汪
    }
}

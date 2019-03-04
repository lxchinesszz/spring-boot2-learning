package app.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import app.service.ZooService;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

/**
 * @author liuxin
 * @version Id: MySqlConfig.java, v 0.1 2019-03-04 11:02
 */
@Configuration
public class ConfigBeans {



  @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
  @Lazy(value = false)
  @Bean(initMethod = "init", destroyMethod = "destroy")
  public ZooService zooService() {
    return new ZooService();
  }
}

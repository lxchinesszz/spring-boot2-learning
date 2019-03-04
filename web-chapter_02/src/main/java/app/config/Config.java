package app.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;


/**
 * @author liuxin
 * @version Id: Config.java, v 0.1 2019-03-04 14:00
 */

@Component("config")
@Profile(value = "test")
public class Config {

  @Value("${application.name}")
  private String name;

  @Override
  public String toString() {
    return "Config{" +
      "name='" + name + '\'' +
      '}';
  }
}

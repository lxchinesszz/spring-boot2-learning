package web.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author liuxin
 * @version Id: RestWebFilterConfig.java, v 0.1 2019-03-06 11:04
 */
@Configuration
public class RestWebFilterConfig {
  @Bean
  public FilterRegistrationBean filterRegistrationBean(){
    FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
    filterRegistrationBean.setFilter(new RestWebFilter());
    filterRegistrationBean.setName("RestWebFilter2");
    filterRegistrationBean.addUrlPatterns("/*");
    return filterRegistrationBean;
  }
}

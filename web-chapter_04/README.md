![](http://p3.pstatp.com/large/pgc-image/576fb4289f374697ac24fe3f6ef12b67)


### 教程目录

![](http://p1.pstatp.com/large/pgc-image/3b41130156a642469dc284346fc845e0)


## SpringBoot2.0学习第四篇之拦截器过滤器配置

### 目标是什么?

1. 过滤器和拦截器的根本区别
2. 快速配置过滤器
3. 快速配置拦截器

### 开发环境

1. JDK1.8或更高版本
2. Maven
3. IntelliJ IDEA 开发工具
4. 开发框架SpringBoot2.x

### 项目目录

```shell
|____resources
|____java
| |____web
| | |____WebApplication.java
| | |____config
| | | |____RestWebFilterConfig.java
| | | |____InterceptorConfig.java
| | | |____LogInterceptor.java
| | | |____RestWebFilter.java
| | |____controller
| | | |____RestWebController.java
```



#### 过滤器和拦截器的根本区别

1. 过滤器是servlet规范规定的，只能用于web程序中，而拦截器是在spring容器中，它不依赖servlet容器。
2. 拦截器属于Spring中的概念,可以在拦截器中使用任何Spring中的Bean信息，而过滤器不属于Spring中的概念点，所以过滤器不行.
3. 过滤器可以拦截几乎所有的请求(包含对静态资源的请求)，而拦截器只拦截Spring中的请求处理器(不拦截静态资源请求)
4. 不管是过滤器还是拦截器都是AOP编程思想的体现。
5. 过滤器的执行顺序在拦截器之前



#### 快速配置过滤器

配置过滤器主要有两种方式

1. servlet自代的注解`@WebFilter`
2. 基于SpringBoot配置`FilterRegistrationBean`

##### Servlet自代的注解`@WebFilter`

先创建过滤器实现类，然后添加在SpringBoot main方法添加`ServletComponentScan`开启扫描Servlet注解

```java
@WebFilter(urlPatterns = "/*", filterName = "RestWebFilter")
public class RestWebFilter implements Filter {
  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
  }

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
    long start = System.currentTimeMillis();
    filterChain.doFilter(servletRequest, servletResponse);
    System.out.println("Execute cost=" + (System.currentTimeMillis() - start));
  }
  @Override
  public void destroy() {
  }
}
```

添加ServletComponentScan开启扫描Servlet注解

```java
//添加ServletComponentScan开启扫描Servlet注解
@ServletComponentScan
@SpringBootApplication
public class WebApplication {
  public static void main(String[] args) {
    SpringApplication.run(WebApplication.class, args);
  }
}
```

##### 基于SpringBoot配置`FilterRegistrationBean`

通过`Configuration`的方式创建Bean,不清楚如何使用`Configuration`的同学可以看上一篇

```java
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
```

使用上面任何一种方式都可以实现过滤器的配置。



#### 快速配置拦截器

**在SpringBoot2.0及Spring 5.0 WebMvcConfigurerAdapter已被废弃。**

拦截器的配置需要注意的是,在Spring5和之前版本有一点小小的区别。如果你之前使用SpringBoot1.x

那么拦截器的配置可能是下面这样。

```java
public class InterceptorConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LogCostInterceptor()).addPathPatterns("/**");
        super.addInterceptors(registry);
    }
}
```

这种方式在SpringBoot2中已经被废弃了。建议使用最新方式。将继承`WebMvcConfigurerAdapter`换成实现`WebMvcConfigurer`

```java
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(new LogInterceptor()).addPathPatterns("/*");
  }
}
```



### 请求耗时拦截器实现

拦截器和过滤器都可以实现日志拦截的功能，也可以对执行类进行耗时的统计，同时也可以获取请求的信息。下面分享一个具有耗时统计的拦截器。

```java
public class LogInterceptor implements HandlerInterceptor {
  private final static Logger logger = LoggerFactory.getLogger(LogInterceptor.class);
  private final static ThreadLocal<Long> processor = new ThreadLocal<>();

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    //根据请求信息判断是否需要拦截
    return true;
  }

  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    logger.info("请求信息打印...");
    processor.set(System.currentTimeMillis());
  }

  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    Long startTime = processor.get();
    Long endTime = System.currentTimeMillis();
    processor.remove();
    // 处理完请求，返回内容
    logger.info("-----------------方法执行完毕,耗时:{}ms-------------------", (endTime - startTime));
  }
}
```





#### 获取本课程代码请关注头条号: 软件编程指南 ，私信: 004

更多了解可以点击小编博客: https://blog.springlearn.cn/posts/4135/
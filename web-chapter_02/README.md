
![image-20190301143438031](https://ws1.sinaimg.cn/large/006tKfTcgy1g0na8ceb0aj30uj0buwl1.jpg)

### 目标是什么?

1. 使用SpringBoot的方式去创建一个Bean，以代替传统通过xml的方式声明Bean
2. Spring中如何优雅的注入配置参数

### 开发环境

1. JDK1.8或更高版本
2. Maven
3. IntelliJ IDEA 开发工具

### 项目目录

```text
|____resources
| |____application-test.properties
| |____spring-context.xml
| |____application.properties
|____java
| |____app
| | |____ConfigApplication.java
| | |____config
| | | |____ConfigBeans.java
| | | |____Config.java
| | |____service
| | | |____ZooService.java
```

### 使用注解

| 注解           | 解释                                                     |
| -------------- | -------------------------------------------------------- |
| @Configuration | 声明配置类(从指定类中读取@Bean标记的方法,并以此创建Bean) |
| @Scope         | 工厂创建方式: 单例或者原型                               |
| @Lazy          | 是否懒加载,默认为true非懒加载即(容器启动就加载)          |
| @Bean          | 声明一个Bean                                             |
| @Profile       | 容器允许的配置文件                                       |
| @Value         | 根据表达式插入配置属性值                                 |



### 一. 创建Bean

1. 配置类的方式

```java
//声明为一个配置类
@Configuration
public class ConfigBeans {
  //声明单例还是原型
  @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
  //是否为懒加载。默认为懒加载就是容器启动后就直接创建实例
  @Lazy(value = false)
  //声明bean的信息等同于xml的方式
  @Bean(initMethod = "init", destroyMethod = "destroy")
  public ZooService zooService() {
    return new ZooService();
  }
}
@SpringBootApplication
public class ConfigApplication {
  //声明为非web应用，只要加载容器即可
  public static void main(String[] args) {
      new SpringApplicationBuilder()
          .web(WebApplicationType.NONE)
          .sources(ConfigApplication.class)
          .run(args);
  }
}
```

2. xml的方式

```xml
spring-context.xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xsi:schemaLocation="
        http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-4.0.xsd" default-lazy-init="false">

    <bean class="app.service.ZooService" id="zooService2" init-method="init" destroy-method="destroy" scope="singleton" lazy-init="false"></bean>
</beans>
```



以上就是xml的方式创建Bean，和配置类的方式来创建Bean，下面我们来验证下。

#### 测试创建Bean是否生效

```java
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
```



### 二. 注入配置文件

在SpringBoot中配置文件的命名方式为:

`application.properties` = `application-default.properties`

SpringBoot项目启动会先加载默认的配置文件也就是`application.properties`

但是一个项目中可能有很多的配置文件,因为SpringBoot也提供了加载多个配置文件的机制,通过在

`spring.profiles.active=test,default`的方式来激活要加载并允许使用的配置文件。对于这种文件的命名是下面这种方式。`application-{profile}.properties`

#### application.properties

激活允许的允许的配置文件test,default

```map
logging.level.app=info
#激活允许的加载的配置文件
spring.profiles.active=test,default
application.name =config
```

因为要演示@Profile的使用方式，所以小编在application.properties和application-test.properties这两个配置文件都声明了`application.name`这个属性。那么我们如何来指定注入的那个配置文件中的属性值呢?

这里其实就是要用`@Profile`来声明，但是一定要注意的要在`application.properties`中激活允许的加载的配置文件`spring.profiles.active=test` 这样就也可以使用`application-test.properties`中的配置参数了。

```java
@Component("config")
//声明从application-test.properties中获取参数并注入到当前类中
//如果不使用该注解默认会加载application.properties中的参数
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
```

可以看到@Value其实就是用来指定要插入的属性的，@Value其实还能插入更多的类型

```java
　　 // 注入普通字符串
	@Value("软件编程指南")
    private String normal;
	// 注入操作系统属性
    @Value("#{systemProperties['os.name']}")
    private String systemPropertiesName;
	//注入表达式结果
    @Value("#{ T(java.lang.Math).random() * 100.0 }")
    private double randomNumber;
	// 注入其他Bean属性：注入beanInject对象的属性another
    @Value("#{beanInject.another}")
    private String fromAnotherBean;
	// 注入文件资源
    @Value("classpath:application.properties")
    private Resource resourceFile;
	// 注入URL资源
    @Value("https://blog.springlearn.cn")
    private Resource testUrl;
```



### 获取本课程代码请关注头条号: 软件编程指南

- 获取方式,私信: 002
  小编编辑很辛苦，希望得到您的点击关注，和小编一起学习SpringBoot。

更多了解可以点击小编博客: https://blog.springlearn.cn/posts/4135/


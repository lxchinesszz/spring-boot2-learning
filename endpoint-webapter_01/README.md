



![](http://p3.pstatp.com/large/pgc-image/576fb4289f374697ac24fe3f6ef12b67)



> 什么是端点? 端点就是SpringBoot通过web或者jmx的方式向外部暴露应用的信息,或者上下文的信息。SpringCloud-Admin就是根据此技术来进行实现的。他们用到的技术就是@Endpoint，而不是通过自己@GetMapping之类进行实现的。下面小编就带大家一起来学习端点的使用。学会本文后在利用前面我们讲过的autoconfigure的自动化配置后，你就可以开发更高级的SpringBoot应用(非业务系统)。本教程将带你从业务系统开发者转变为研发系统开发者。

用过SpringBoot的同学可能知道，SpringBoot有很多监控端点,比如当我们引入健康监控组件

![image-20190308190916705](https://ws2.sinaimg.cn/large/006tKfTcly1g0vli8ltdtj30gl02odhf.jpg)

系统就会自动暴露出许多,web端口供外部调用，获取应用的信息，或者上下文的信息。

![image-20190308191019856](https://ws2.sinaimg.cn/large/006tKfTcly1g0vljegsf3j311405sk2n.jpg)



### 自定义`Web`端点使用到的注解

可以使用`@Endpoint`,`@WebEndpoint`,`@JmxEndpoint`,或者`EndpointWebExtension`来实现HTTP方式的端点,可以是传统SpringMVC也可以是最新的`Spring WebFlux`



- `@Endpoint`相当于`@WebEndpoint`和`@JmxEndpoint`的整合。web和jmx方式都支持

- `@WebEndpoint` 只会生成web的方式的端点监控

![image-20190308190517126](https://ws1.sinaimg.cn/large/006tKfTcly1g0vljqvkpdj30r80du0uc.jpg)

- `JmxEndpoint` 只会生成Jmx的方式监控

![image-20190308183731989](https://ws1.sinaimg.cn/large/006tKfTcly1g0vljrovxlj30o80j577a.jpg)

| Operation          | HTTP method |
| ------------------ | ----------- |
| `@ReadOperation`   | `GET`       |
| `@WriteOperation`  | `POST`      |
| `@DeleteOperation` | `DELETE`    |

### PATH

默认的基础路径是` /actuator`,如果一个端点配置的路径是`sessions`,那么它的全路径就是`/actuator/sessions`

```java
@Component
@WebEndpoint(id = "sessions")
public class MyHealthEndpoint {
  @ReadOperation
  public Info get(@Selector String name) {
    return new Info(name, "23");
  }
}
```

`@Selector` 的含义是让这个路径变成`/actuator/sessions/{name}` 我们能从路径上获取一个入参。

#### 自定义管理端点路径

`management.endpoints.web.base-path = /manage`

此配置会将`/actuator/sessions/{name}`转换成`/manage/sessions/{name}`

#### 自定义管理服务器地址

默认端口和应用的端口是一致的,但是也可以通过配置的方式改变端口

```
management.server.port = 8081
management.server.address = 127.0.0.1
```

#### 激活端点

```
//激活所有的端点的web方式请求
management.endpoints.web.exposure.include=*
//关闭端点web方式
management.endpoints.web.exposure.exclude=env,beans
//激活所有的JMX方式请求
management.endpoints.jmx.exposure.include=*
```

#### 跨域方式请求

```
//允许跨域的网址
management.endpoints.web.cors.allowed-origins=http://example.com
//允许跨域的方法
management.endpoints.web.cors.allowed-methods=GET,POST
```



最后跟着小编来实现一个. 私信: 006 获取代码地址

![image-20190308191647982](https://ws4.sinaimg.cn/large/006tKfTcly1g0vlq3jpyij313w0lke81.jpg)

#### 总结:

最后我们来总结。

其实@WebEndpoint 就相当于`声明成一个@RestController`的控制类

而请求方法分别被下面注解代替。

| Operation          | HTTP method |
| ------------------ | ----------- |
| `@ReadOperation`   | `GET`       |
| `@WriteOperation`  | `POST`      |
| `@DeleteOperation` | `DELETE`    |

#### 获取本课程代码请关注头条号: 软件编程指南 ，私信: 005

更多了解可以点击小编博客: https://blog.springlearn.cn/posts/4135/
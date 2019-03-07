

> 何为自动化配置?

**通读全文大概需要1分钟**

![](http://p3.pstatp.com/large/pgc-image/576fb4289f374697ac24fe3f6ef12b67)

不知道用过SpringBoot的同学,有没有发现在SpringBoot的maven依赖中经常会有很多的starter之类的依赖,往往这些依赖的框架,在加入到pom之后，当应用启动时候就会自动的被应用整合起来

比如昨天我们的thymeleaf教程中,SpringBoot在整合`thymeleaf`的时候，只是引入了`spring-boot-starter-thymeleaf`依赖就可以直接使用了，类似的还有很多。



比如: 自动整合`freemarker`

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-freemarker</artifactId>
</dependency>
```

自动整合mybatis

```xml
<dependency>
 	<groupId>org.mybatis.spring.boot</groupId>
    <artifactId>mybatis-spring-boot-starter</artifactId>
</dependency>
```



现在不懂这些都没关系，本节课小编就教大家如何使用自动化配置。



说道自动化配置这其实是SpringBoot的一个小特性。不知道有没有同学仔细研究过`SpringBootApplication`这个注解主要有什么作用。其实自动化配置的秘密就隐藏在这里。

![image-20190307215219386](https://ws4.sinaimg.cn/large/006tKfTcly1g0uklm48qmj30n6034tbn.jpg)

跟着小编来找一下秘密到底在哪里。

![image-20190307215359080](https://ws4.sinaimg.cn/large/006tKfTcly1g0ukncjlugj30qo04yteh.jpg)

秘密就是: `EnableAutoConfiguration`

这个类的含义就是自动在依赖中找到自动化配置类。为了更简单的给各位小伙伴说清出。下面

小编举一个例子`thymeleaf`。我们看下`thymeleaf`是如何实现自动化配置的吧。`thymeleaf`的自动化类就是这个:

`ThymeleafAutoConfiguration `。 前面我们说了SpringBoot的自动化配置的秘密是`EnableAutoConfiguration`.。那么我们看`ThymeleafAutoConfiguration`和`EnableAutoConfiguration`到底有什么关系呢?

### 谜底大解开

SpringBoot项目启动类必须要被`EnableAutoConfiguration`标记，而之所以我们没有看到是因为，`SpringBootApplication`已经被`EnableAutoConfiguration`标记了。

![image-20190307220020658](https://ws4.sinaimg.cn/large/006tKfTcly1g0uktyizrlj30hf037gnq.jpg)

那么SpringBoot会在他依赖的所有包中去查找一个叫`spring.factories`的文件,`ThymeleafAutoConfiguration`和`EnableAutoConfiguration`的关系就在`spring.factories`里面声明。我们打开

`spring.factories`文件发现`EnableAutoConfiguration`其实于很多的自动化配置做了绑定。当`EnableAutoConfiguration`被加载其实与其绑定的这么多自动化配置都会选择的进行启动。

![image-20190307220530562](https://ws4.sinaimg.cn/large/006tKfTcly1g0ukzc9xcqj31270ev1kx.jpg)

![image-20190307220551258](https://ws1.sinaimg.cn/large/006tKfTcly1g0ukzowla4j311s0f94qp.jpg)

到这里我们就知道了为啥当引入了`thymeleaf`就会自动被整合到SpringBoot中了吧。如果只知道以上这些其实还是不够的。下面小编提出一个问题。

##### 请思考：

**SpringBoot中自动化配置会自动整合`ThymeleafAutoConfiguration`但是假如我们不引入`thymeleaf`它的实现类，那么自动整合时候就会报中`NoSuchClassException`异常。SpringBoot是如何解决呢？**



我们看`ThymeleafAutoConfiguration`是如何实现的。

![image-20190307221503220](https://ws3.sinaimg.cn/large/006tKfTcly1g0ul99nqlej30qc0ha7np.jpg)

答案就在这些注解上面。

- @ConditionalOnClass ： classpath中存在该类时起效
- @ConditionalOnMissingClass ： classpath中不存在该类时起效
- @ConditionalOnBean ： DI容器中存在该类型Bean时起效
- @ConditionalOnMissingBean ： DI容器中不存在该类型Bean时起效
- @ConditionalOnSingleCandidate ： DI容器中该类型Bean只有一个或@Primary的只有一个时起效
- @ConditionalOnExpression ： SpEL表达式结果为true时
- @ConditionalOnProperty ： 参数设置或者值一致时起效
- @ConditionalOnResource ： 指定的文件存在时起效
- @ConditionalOnJndi ： 指定的JNDI存在时起效
- @ConditionalOnJava ： 指定的Java版本存在时起效
- @ConditionalOnWebApplication ： Web应用环境下起效
- @ConditionalOnNotWebApplication ： 非Web应用环境下起效

SpringBoot如何来判断是否启动自动化配置，防止报错呢? 就是通过上面的条件注解来实现。只要满足条件的配置类，才会被整合进去。



### 实现一个自动化配置项目实战？

我们以github上一个项目为例

![image-20190307221949850](https://ws4.sinaimg.cn/large/006tKfTcly1g0ule8b19qj30ph08eaay.jpg)

使用方法.（和SpringCloud中Hystrix的使用方法类似，当getUserName出现报错之后,就返回其指定的备用方法）

```java
@Service
public class UserServiceImpl  {


  @TurnoffCommand(fallbackMethod = "getBreakUserName")
  public String getUserName(String name) {
    throw new RuntimeException();
  }

  public String getBreakUserName(String name) {
    return "Mock用户id:" + name;
  }

}
```

该项目可以自动化配置.

```xml
  <!--熔断-->
<dependency>
   <groupId>com.github.lxchinesszz</groupId>
   <artifactId>turnoff-spring-boot-starter</artifactId>
   <version>1.0.1</version>
</dependency>
```

我们看他是如何实现自动化配置的吧。

Turnoff的配合类就下面这么简单。

![image-20190307222656553](https://ws2.sinaimg.cn/large/006tKfTcly1g0ullmomqlj30nd042jvj.jpg)

只用在META-INF目录中创建一个spring.factories就可以实现。是不是很简单。

![image-20190307222745611](https://ws4.sinaimg.cn/large/006tKfTcly1g0ulmhpl6sj30t709l14n.jpg)



到这里本篇内容就讲完了，相信各位看官已经明白了吧。



#### 获取本课程代码请关注头条号: 软件编程指南 ，私信: 005

更多了解可以点击小编博客: https://blog.springlearn.cn/posts/4135/
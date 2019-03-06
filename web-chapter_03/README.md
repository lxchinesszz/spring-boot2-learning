![](http://p3.pstatp.com/large/pgc-image/576fb4289f374697ac24fe3f6ef12b67)


### 教程目录

![](http://p1.pstatp.com/large/pgc-image/3b41130156a642469dc284346fc845e0)




### SpringBoot2.0学习第三篇之整合thymeleaf模板引擎.md

#### 目标是什么?

1. 快速整合thymeleaf模板引擎
2. thymeleaf模板基础语法

#### 开发环境

1. JDK1.8或更高版本
2. Maven
3. IntelliJ IDEA 开发工具

#### 项目目录

```
.
|____resources
| |____templates
| | |____index.html
|____java
| |____hello
| | |____WebApplication.java
| | |____controller
| | | |____HelloController.java
```



### 一. 快速整合thymeleaf视图模板引擎

```java
  System.out.println(String.format("hello:%d", 1));
  System.out.println(String.format("hello:%s", "SpringBoot"));
```

首先我们先认识一下什么是视图模板引擎,其实简单的来看,以上代码就是一个模板引擎。何为模板引擎其实就是可以将不同数据类型,通过处理后,给添加到文本上。像下面这种%s就是String类型的占位符,%d是整型的占位符,而高级的一点的模板引擎是支持更多的语法,比如支持for循环，while循环，条件判断。就比如thymeleaf，或者是freemaker,或者是jsp之类的模板引擎都是支持的，那么为啥要在前面加一个视图模板引擎呢，是因为渲染的文本最终会被转换成html的格式，输出给客户端,客户端如果是浏览器访问,就会把文本直接渲染成可视化的方式展示给用户。

- 本文我们给大家演示的模板引擎是thymeleaf，他默认的模板文件是以.html的方式。

- 而上面所说的freemaker引擎,默认的模板文件是.ftl

在SpringBoot的配置文件中,如果不配置,默认就是下面这样写,当然我们也可以自定义模板文件的后缀,和模板文件所在的路径



1. 默认模板文件后缀

```
spring.freemarker.suffix=.ftl
spring.thymeleaf.suffix=.html
```

2. 默认模板文件路径

`resources/templates`

3. 静态文件路径（不需要渲染的页面或者是css,js之类的放这个目录）

`resources/static`



**好了,在知道以上这些知识储备后,我们就开始快速的整合thymeleaf吧！**



#### 首先添加依赖

```xml
<dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-thymeleaf</artifactId>
        </dependency>
</dependencies>
```

#### 添加视图模板文件

在resources/templates创建,`index.html`。 注意因为我们整合的是`thymeleaf`所以我们的后缀要是`.html`。

如果要是整合`freemarker`那么我们文件名就要命名为`index.ftl`

```html
<!DOCTYPE HTML>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>软件编程指南: Serving Web Content</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>
<body>
<a href="https://www.toutiao.com/c/user/3686495601/#mid=1563737358895105"><img src="https://ws1.sinaimg.cn/large/006tKfTcgy1g0na8ceb0aj30uj0buwl1.jpg"></a>
<p th:text="'Hello, ' + ${name} + '!'" />
</body>
</html>
```

**注意语法:**

`<p th:text="'Hello, ' + ${name} + '!'" />`

#### 添加控制器

可以看到在本节课程中,我们使用的是`@Controller`注解,而不是`@RestController`,那是因为本节我们是要需要视图模板引擎处理后才能将数据响应给客户端,而`@RestController`适合使用在不需要模板引擎处理,直接把数据响应给客户端的场景下。这里入参name是请求参数,`Model`和`ModelAndView`是自动注入进来的。

```java
@Controller
public class HelloController {

  @GetMapping("/hello")
  public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model) {
    model.addAttribute("name", name);
    return "index";
  }
   @GetMapping("/hello2")
  public ModelAndView hello2(@RequestParam(name = "name", required = false, defaultValue = "World") String name, ModelAndView mv) {
    mv.setViewName("index");
    mv.addObject("name", name);
    return mv;
  }
}
```

当然也可以不自动注入,我们自己创建。像下面这种。

```java
 @GetMapping("/hello3")
  public ModelAndView hello3(@RequestParam(name = "name", required = false, defaultValue =   "World") String name) {
    ModelAndView mv = new ModelAndView("index");
    mv.addObject("name", name);
    return mv;
  }
```

- Model 就是视图层渲染所需要的数据
- ModelAndView 就是视图层渲染所需要的数据和要渲染的视图



### 运行启动

```java
@SpringBootApplication
public class WebApplication {

  public static void main(String[] args) {
    SpringApplication.run(WebApplication.class, args);
  }
}
```

启动类还是一个`Main`函数

该`main()`方法使用Spring Boot的`SpringApplication.run()`方法启动应用程序。您是否注意到没有一行XML？也没有**web.xml**文件。此Web应用程序是100％纯Java，您无需处理配置任何管道或基础结构。



**恭喜！您刚刚使用Spring Boot开发了一个网页。**



---

### thymeleaf模板基础语法

前面说了这些高级的模板引擎是可以处理各种类型的数据,也支持想for语句或if语句的语法，下面我们来看看如何使用。先看下控制类

```java
@Controller
public class SyntaxDemoController {
  @GetMapping("/book")
  public String index(Model model) {
    Book book = new Book("颈椎病康复指南", "项目经理", new Date(), 23.95,"CG");
    model.addAttribute("book", book);
    model.addAttribute("title", "语法演示");
    model.addAttribute("books", loadBooks());
    model.addAttribute("myMap",loadMap());
    return "book";
  }
  public Map<String,String> loadMap(){
    Map<String,String>map=new ConcurrentHashMap<>();
    map.put("医生","C");
    map.put("教授","B");
    map.put("演员","C");
    return map;
  }
  public List<Book> loadBooks() {
    List<Book> books = new ArrayList<>();
    books.add(new Book("Java开发指南", "Jay", new Date(), 99.2,"PT"));
    books.add(new Book("Python从入门到放弃", "Lin", new Date(), 43.8,"CX"));
    books.add(new Book("NodeJs实战", "XD ", new Date(), 234.23,"*"));
    return books;
  }
}

```



1. 变量赋值

`<title th:text="${title}"></title>`

2. 对象赋值

```html
 <tr>
        <td th:text="${book.getName()}">默认值</td>
        <td th:text="${book.getAuthor()}">默认值</td>
        <td th:text="${#dates.format(book.getDate(), 'yyyy-MM-dd hh:mm:ss')}">默认值</td>
        <td th:text="${book.getPrice()}">默认值</td>
 </tr>
```

在数据模型`Model` 中添加一个变量book，就可以直接在目标中使用（Book必须有getset方法）

3. switch语法

```html
<!--switch判断-->
<td th:switch="${book.getSaleType()}">
  <span th:case="'CG'">闪购</span>
  <span th:case="'PT'">拼团</span>
  <span th:case="'CX'">促销</span>
  <span th:case="*">其它</span>
</td>
```

4. 集合赋值

```html
<!--读物实体列表-->
    <tr th:each="product : ${books}">
        <td th:text="${product.getName()}">默认值</td>
        <td th:text="${product.getAuthor()}">默认值</td>
        <td th:text="${#dates.format(product.getDate(), 'dd-MM-yyyy')}">默认值</td>
        <td th:text="${'￥' + #numbers.formatDecimal(product.getPrice(), 1, 2)}">默认值</td>
        <!--switch语法-->
        <td th:switch="${product.getSaleType()}">
            <span th:case="'CG'">闪购</span>
            <span th:case="'PT'">拼团</span>
            <span th:class="${product.getPrice() gt 40}?'offer'" th:case="'CX'">促销</span>
            <span th:case="*">其它</span>
        </td>
    </tr>
```

5. if判断

```html
<!--if语句语法-->
<span th:if="${book.getPrice() gt 60}" class="offer">if语句语法</span>
```

6. map集合

```html
 <!--map-->
 <div th:each="key,v:${myMap}">
   <p th:text="${'索引:'+v.index+'Key:'+key.getKey()+'value:'+key.getValue()}"></p>
 </div>
```





### 获取本课程代码

- 获取方式,私信: 003
  小编编辑很辛苦，希望得到您的点击关注，和小编一起学习SpringBoot。

更多了解可以点击小编博客: https://blog.springlearn.cn/posts/4135/



![image-20190301143438031](https://ws1.sinaimg.cn/large/006tKfTcgy1g0na8ceb0aj30uj0buwl1.jpg)

### 目标是什么?

构建一个RESTful Web服务的过程。简单来讲就是区别于传统的Web服务,传统Web服务是需要视图引擎的，即做一个网站之类的,而RESTful Web服务主要是做数据处理的。因为它并不返回可视化的页面。只是处理后的数据而已

### 开发环境
1. JDK1.8或更高版本
2. Maven
3. IntelliJ IDEA 开发工具
### 创建项目目录

```
└──src
    └──main
        └──java
            └──rest
```

### 注解使用实例

- GetMapping
- PostMapping
- PutMapping
- PatchMapping
- DeleteMapping

以上五个注解都包括下面这些属性

1. name `请求地址`
2. value `请求地址`
3. path  `请求地址`
4. params `请求参数限制`
5. headers `请求头限制`
6. consumes `客户端到服务端数据类型限制`
7. produces `服务端到客户端的数据类型限制`

以上这些注解其实就等于RequestMapping。
![](http://p3.pstatp.com/large/pgc-image/3bf5fea0d3624881912b378c4162f816)

- name、value、path这三个属性是一样的，都是声明这个方法的请求路径

- params和headers这两个属性一个是对请求参数的限制，一个是对请求头的限制。

eg:
![](http://p1.pstatp.com/large/pgc-image/f7b7b64340f54063b4ee0f56cd1ac001)

如图所示,第一个就是限制请求参数text文本不能为Spring

第二个就是限制请求头必须要包含有name=admin

![](http://p3.pstatp.com/large/pgc-image/21a3af73b9fb47bbab434358a65e2a8c)

consumes就是控制客户端到服务端请求的数据类型和请求方式。

produces 服务端到客户端的数据类型限制。

这部分的演示如果不清楚可以关注看小编的视频演示。


### 如何启动一个SpringBoot服务

![](http://p1.pstatp.com/large/pgc-image/b63262967887465a92ba49444aaf0abd)

使用注解SpringBootApplication来标记一个启动类。

在main函数中运行SpringApplication.run(RestApplication.class,args);即可启动一个内嵌web容器的SpringBoot项目

![](http://p1.pstatp.com/large/pgc-image/6f09a678f8be4832b2eceb3de185a60a)

服务层要使用RestController而不能使用传统Web服务的Controller注解。


**他们的区别是:**

- RestController主要是构建RESTful Web服务使用，它会根据produces属性指定的类型而对数据进行转换返回。
- 而Controller返回的是一个视图模型，要经过视图引擎渲染使用。

不过Controller+ResponseBody其实就等于RestController

### RequestParam

1. 给变量声明一个别名
2. 设置默认值
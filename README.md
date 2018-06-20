# SpringBoot2.0-learn
SpringBoot2.0开发教程

>本篇教程是对SpringBoot2.0为技术起点,开始的。主要学习SpringBoot2.0的WebFlux

那么我们首先了解下SpringBoot2.0的新特性


### SpringBoot2.0新特性

1. 编程语言Java8+,和当前火爆的Kotlin
2. 底层框架Spring Framwork 5.0.x
3. 全新特性Web Flux（可以说我们学习SpringBoot2.0就是学习这个）


#### 为什么编程语言要从Java8开始呢？ 一个重要的原因就是Java8的Lambda表达式和Stream流处理

那么当读到这里我们我们再看，上面开篇截取Spring.io官网的图片。我们就能对SpringBoot1.0和2.0在心里有一个判断

- SpringBoot1.0是仅支持Servlet Containers->Servlet API属于传统方式
- SpringBoot2.0在支持1.0的特性上,同时添加了一个新特性就是WebFlux,可以使用Netty及Servlet3.1作为容器,基于
   Reactive Streams 流处理。

#### 那么我们在分析Servlet3.0之前和3.0的区别？

- 3.0之前Servlet 线程会一直阻塞，只有当业务处理完成并返回后时结束 Servlet线程。

- 而3.0规范其中一个新特性是异步处理支持,即是在接收到请求之后，Servlet 线程可以
将耗时的操作委派给另一个线程来完成，在不生成响应的情况下返回至容器

这样说可能大家还不太容易理解，我们来举一个例子

eg： 我们设置tomcat最大线程为200

那么当有200个线程同时并发在处理,那么当来201个请求的时候,就已经处理不了，以为所有的线程都阻塞了。
而3.0之后异步处理是怎样处理呢？是学过Netty通信框架的同学会比较容易理解一点，
Servlet3.0类似于Netty一样就一个boss线程和work线程，boss线程只负责接收请求,work线程只负责处理逻辑。
那么servlet3.0会这200个线程只负责接收请求，然后每个线程将受到的请求，转发到work线程去处理。以为这个200个线程
只负责接收请求，并不负责处理逻辑，故不会被阻塞，影响通信,其主要应用场景是针对业务处理较耗时的情况
可以减少服务器资源的占用，并且提高并发处理速度。

### 项目实战


在做项目实战的时候，我们要对下面两个问题有所了解！

1. 我们知道SpringMVC是通过#@Controller和@RequestMapping来定义路由的那么WebFlux是怎么定义路由的？

我们看上图(下面的这个两个区别要注意，项目中会遇到)
  ● 基于 Spring MVC 注解 @Controller 等
  ● 基于 Functional 函数式路由是 RouterFunctions


2. Flux和Mono分别是什么，在小编看来Flux和Mono都是一个数据的载体,不同的是

- Flux  一种集合(0,n)
- Mono  一个实体包装(0,1)


好了，当我们了解了这些，现在开始我们的学习吧!

## 目录

- chapter_01 创建一个web项目
- chapter_02 定义接口
- chapter_03
- chapter_04

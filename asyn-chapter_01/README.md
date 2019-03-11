**通读全文大概需要1分钟**

![](http://p3.pstatp.com/large/pgc-image/576fb4289f374697ac24fe3f6ef12b67)

> 异步任务 ?

异步常用来处理对性能要求比较高的应用，通过减少阻塞，增加并行查询，来提高性能。其实实现异步任务也非常的简单，在Java中也有原生的API。本篇文章教你如何在SpringBoot项目下，优雅方便的使用异步编程。


**异步任务实现其实就是利用submit,来提交任务,通过线程池的方式来异步执行**
```java
public interface ExecutorService extends Executor {
	<T> Future<T> submit(Callable<T> task);
}
```

但是一般使用的时候,需要我们自己去创建线程执行器.如下:

```java
ExecutorService es = Executors.newSingleThreadExecutor();
//开启2个异步并行查询,并将结果添加到list中
List<Future<?>> result = new ArrayList();
result.add(es.submit());
result.add(es.submit());
....
//通过获取查询结果
for(Future<?> f:result){
    //遍历处理查询结果
	f.get();
}
```

常见的异步编程就像上面一样，将不需要同步获取结果的查询，使用异步的方式进行查询。在使用的时候在同步阻塞获取结果。



### 什么场景适合异步?

1. 业务流程查询或者执行，不需要同步立马获取返回值或者立马执行完成
2. 具有一个或以上的并行执行

当具有以上两个特性的时候,毫无疑问是非常适合异步来处理的，这样能大大的提高执行效率和执行时间。



这就是异步的使用。而我们本篇将的SpringBoot更加简化了代码。在SpringBoot中我们可以将我们任何的业务类，方法通过`@Asyn`的方式将方法由同步转换为异步。



# SpringBoot带来了更简单的方式

SpringBoot来如何简化操作呢? 在SpringBoot中开发者不需要自己来实现Runnable接口，只要在任何的bean中使用@Asyn注解就可以实现。就和前面一样能带来性能提高。当然前提是要适合自己的业务场景。适合自己的才是最好的。



![](http://p1.pstatp.com/large/pgc-image/c334b0adb3c144f9896afe559523c089)



在每个方法中都睡眠1s,当并行查询时候三个方法最好的情况是一起执行。因此三个方法同时调用最好情况只会耗时1s。而假如不使用异步，三个方法调用,只有一种情况就是，按照调用先后顺序执行，耗时最少是3s。

**该say,方法使用Spring的@Async注释进行标记，表明它将在单独的线程上运行。**

![](http://p3.pstatp.com/large/pgc-image/f3f8412ab1dd42258d9d4b16ce669277)

![image-20190311153141223](https://ws1.sinaimg.cn/large/006tKfTcgy1g0yw2oe19xj30q205ntd5.jpg)


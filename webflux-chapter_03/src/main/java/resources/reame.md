

```
subscribe(); ① 

subscribe(Consumer<? super T> consumer); ②

subscribe(Consumer<? super T> consumer,
          Consumer<? super Throwable> errorConsumer); ③

subscribe(Consumer<? super T> consumer,
          Consumer<? super Throwable> errorConsumer,
          Runnable completeConsumer); ④

subscribe(Consumer<? super T> consumer,
          Consumer<? super Throwable> errorConsumer,
          Runnable completeConsumer,
          Consumer<? super Subscription> subscriptionConsumer); ⑤
```



① 订阅并触发序列。
② 对每个产生的价值做一些事情。
③ 处理值，但也会对错误做出反应。
④ 处理值和错误，但也会在序列成功完成时执行一些代码。
⑤ 处理价值观和错误，并成功完成，但也做Subscription这个subscribe调用产生的东西 。

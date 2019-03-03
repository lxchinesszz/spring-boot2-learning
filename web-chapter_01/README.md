


![image-20190301143438031](https://ws1.sinaimg.cn/large/006tKfTcgy1g0na8ceb0aj30uj0buwl1.jpg)

### 目标是什么?

构建一个RESTful Web服务的过程。

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
6. consumes `请求数据类型控制`
7. produces `响应类型控制`

### 五个属性的含义视频演示

以上五个注解其实等同于`RequestMapping`这一个注解，`RequestMapping`比上面多了一个属性就是

```java
RequestMethod[] method() default {};
```

```java

    @RequestMapping(path = "/ge7",method = RequestMethod.GET)
    @GetMapping(path = "/ge7")

    @RequestMapping(path = "/ge7",method = RequestMethod.POST)
    @PostMapping
```

等价

### RequestParam

1. 给变量声明一个别名
2. 设置默认值
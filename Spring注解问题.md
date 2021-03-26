# Spring使用注解反射方法——眼见并非为实

## 现象

在Spring中使用注解标注方法时，如果方法为private，则引用的bean为空。调用一个非注解的private方法则bean顺利依赖。

~~~java
    
	@Autowired
    private TestPrivateService testPrivateService;

// 正常输出
    @RequestMapping("/first")
    public String first(){
        test();
        return "first controller";
    }
// 空指针异常
    @RequestMapping("/second")
    private String second(){
        test();
        return "second controller";
    }
    private void test(){
        testPrivateService.test();
    }
~~~

## 原理分析

Spring使用反射机制加载Bean，生成代理类，扫描所有的public方法。因此静态变量和私有方法都不会被加载。但此处正常输出和空指针异常差异点在调用方的private和public方法，因此需要分析的重点在于注解的加载机制。

分析注解机制可以发现，注解扫描的时候并不会反射私有方法。

~~~java
    @Pointcut("execution(* com.test.spring.example.demo.controler.*.*(..))")
    public void webLog(){
       log.info("LogAspect.webLog");
    }
~~~

编写Spring中对应的切片发现，切片无法切到/second方法接口，但是可以拦截到/first接口。因此验证了切片并不能拦截到私有方法。

此时就有另一个问题，为什么RequestMapping注解可以把私有方法注册URL绑定起来。
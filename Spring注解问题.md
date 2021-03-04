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
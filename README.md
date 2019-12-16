# spring boot小笔记 

# spring项目热部署

# @ConfigurationProperties
* [好的网络资料](https://aisensiy.github.io/2017/08/31/spring-boot-configuration-properties-and-value/)
* 需要引入依赖
```xml
<!--使用@ConfigurationProperties需要-->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-configuration-processor</artifactId>
    <optional>true</optional>
</dependency>
```
* 经验证，使用@ConfigurationProperties时不用在启动类处添加@EnableConfigurationProperties注解,查看其源码有：
> Standard Spring Beans will also be scanned regardless of this value
* 与@Value的区别，两者的功能都是用来从配置文件中获取配置项，@Value适合用来配置项少的场景，@ConfigurationProperties适合用在配置项多的场景；两者不能在同一个类中共用；
* 可以配合`@PropertySource`来引入其它的配置文件；

# spring boot actuator
## [极好的网络资料](https://www.baeldung.com/spring-boot-actuators#boot-2x-actuator)
## 注意
* Spring Boot 1.x与Spring Boot 2.x中的actuator有很大不同；
* Spring Boot 1.x中的端点默认是开启的，而Spring Boot 2.x中的端点默认都是关闭的；
* 这里引入的是Spring Boot 2.x版本的actuator;
* Spring Boot 2.x版本的actuator的访问前缀为`/actuator`（是可以通过代码改变的）

## 使用
* 引入依赖
```xml
<!--spring boot actuator需要-->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```
* 开启所有端点
在application.properties文件中设置
```properties
management.endpoints.web.exposure.include=*
```
* 项目启动
* 本地访问`http://localhost:8080/actuator`

## 常见端点
* /beans； 即访问`http://localhost:8080/actuator/beans`，下同
* /env；
* /conditions(即1.x中的/autoconfig)
* /threaddump：dumps the thread information of the underlying JVM
* /configprops：allows us to fetch all @ConfigurationProperties beans

# SpringApplication.run()与AnnotationConfigApplicationContext启动方式备忘
1. 实际项目使用中一般使用`SpringApplication.run()`的方式，其默认开启了许多功能，如配置文件加载，属性自动注入等，启动过程会较耗时一些。
开发中有时为了便于测试某些功能，每次都启动一次效率会有些低下，此时可使用`AnnotationConfigApplicationContext`来“轻”启动项目，
AnnotationConfigApplicationContext一般结合@Configuration使用，其只会实例化@Configuration下的@Bean注释的方法，
因此当需使用配置文件或者其它功能时均需要手动开启(如@EnableConfigurationProperties，@PropertySource)。可参考PropertiesTest.java




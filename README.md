# spring boot小笔记 

# 功能
## spring项目热部署
## @ConfigurationProperties
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
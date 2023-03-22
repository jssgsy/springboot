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
* 可以配合`@PropertySource`来引入其它的配置文件；
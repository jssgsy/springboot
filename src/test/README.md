[toc]

# 范例
```java
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootApplicationStarterTests {

	@Test
	public void contextLoads() {
	    
	}
}
```
由此可知，只需要在测试类加`@SpringBootTest`与`@RunWith(SpringRunner.class)`即可；

# 测试时指定profile
只需要在测试类上加@ActiveProfiles即可，假如要指定profile为test：
```
@ActiveProfiles("test")
```

# 关于logback-test.xml
经验证，logback的配置文件名字必须命名为logback-test.xml才生效。

# 关于配置文件
## 资源文件
默认情况下，资源文件(如application.yml)必须放在test/resources目录下，不会去读取main/resources目录；
  * 可以在pom文件中设置读取main/resources下的资源文件；
  * 这是maven本身的机制，与springboot无关

## 关于logback日志文件：
* 优先级为： 
  1. 先找logback-test.xml；一般用这个就可以了
  2. 再找logback.xml；
  3. 都找不到则用默认的；
* 一般情况下，测试环境的logback配置文件不用线上那样复杂，直接输出到console即可；
* [spring-boot-using-logback-test-xml-instead-of-logback-xml](https://stackoverflow.com/questions/28956235/spring-boot-using-logback-test-xml-instead-of-logback-xml)

进阶：要知道使用了哪些资源文件，查看编译下test-classes目录下有哪些即可；
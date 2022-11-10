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

# 关于logback-test.xml
经验证，logback的配置文件名字必须命名为logback-test.xml才生效。

# 关于配置文件
* application.properties必须放在test/resources目录下，不会去读取main/resources目录下的application.properties；
* 奇怪的是，当profile设置为dev时，会读取main/resources下的application-dev.properties；
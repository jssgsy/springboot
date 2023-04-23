有多种使用方法，既可使用注解进行声明式，也可以使用RetryTemplate进行编程式

# 注解-@Retryable
经验证，不需要一定为接口，`可直接作用于类的方法上`；
缺点：不能回调?

通过日志可知，底层使用的就是RetryTemplate，可以深究下

# RetryTemplate
优点：编程式、可注册回调函数，当重试时通知自己？


* https://www.baeldung.com/spring-retry

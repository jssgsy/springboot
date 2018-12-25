# spring boot中使用定时任务

## 用法
1. 开启定时功能
    * 在入口类处使用@EnableScheduling注解即可，参考SpringbootApplicationStarter类
2. 将定时任务放在某个类方法中，并用@Scheduled注解此方法即可；
3. 最基本的用法就这么简单，没有其它步骤了；
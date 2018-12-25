package com.univ.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author univ
 * @datetime 2018/12/25 9:17 AM
 * @description 定时任务
 */
@Component  // 注意这里不要忘了使其为bean
public class TaskDemo {

    /**
     * spring boot中被@Scheduled注解的方法就是定时方法，只要开启了spring的定时功能即可
     * @throws InterruptedException
     */
    @Scheduled(cron = "2 * * * * *")
    public void sayHello() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            Thread.sleep(1000);
            System.out.println("当前执行线程：" + Thread.currentThread().getName() + " , i = " + i);
        }
    }
}

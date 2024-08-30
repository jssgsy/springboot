package com.univ.task;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author univ
 * @datetime 2018/12/25 9:17 AM
 * @description 定时任务
 * 注意：如果不使用@Async，则所有任务都是同步的，即一个任务执行完再执行另一个，都是一个线程执行
 */
@Component  // 注意这里不要忘了使其为bean
public class TaskDemo {

    /**
     * spring boot中被@Scheduled注解的方法就是定时方法，只要开启了spring的定时功能即可
     * @throws InterruptedException
     */
//    @Scheduled(cron = "1 * * * * *")
    @Async
    public void sayHello() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            Thread.sleep(1000);
            System.out.println("sayHello方法中，当前执行线程：" + Thread.currentThread().getName() + " , i = " + i);
        }
    }

//    @Scheduled(cron = "1 * * * * *")
    @Async
    public void task2() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            Thread.sleep(1000);
            System.out.println("task2方法中，当前执行线程：" + Thread.currentThread().getName() + " , i = " + i);
        }
    }
}

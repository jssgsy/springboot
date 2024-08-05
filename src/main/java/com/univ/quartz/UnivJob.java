package com.univ.quartz;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * 自定义实现Job类
 * 或者直接实现Job接口也可
 * @author univ
 * date 2024/8/5
 */
@Slf4j
public class UnivJob extends QuartzJobBean {
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        log.info("UnivJob被执行了");
    }
}

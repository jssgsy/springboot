package com.univ.controller;

import com.univ.quartz.UnivJob;
import org.quartz.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author univ
 * date 2024/8/5
 */
@RestController
@RequestMapping("/quartz")
public class QuartzController {

    @Resource
    private Scheduler scheduler;

    // 立即执行
    @GetMapping("/executeNow")
    @ResponseBody
    public String executeNow(@RequestParam("jobId") String jobId, @RequestParam("triggerId") String triggerId) throws SchedulerException {
        // 不论状态是暂停还是执行中
        // 前提是此任务已经有trigger关联在，否则不能直接这样执行，经验证，调用后不用重新调用start方法。
        scheduler.triggerJob(JobKey.jobKey(jobId, "univ_job_group"));
        // 经验证，调用后不用重新调用start方法。
        return "ok";
    }

    @GetMapping("/start")
    @ResponseBody
    public String start(@RequestParam("jobId") String jobId, @RequestParam("triggerId") String triggerId) throws SchedulerException {
        JobDetail jobDetail = JobBuilder.newJob(UnivJob.class).withIdentity(jobId, "univ_job_group").build();
        CronTrigger build = TriggerBuilder.newTrigger().withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ? *"))
                .withIdentity(triggerId, "trigger_group").build();
        scheduler.scheduleJob(jobDetail, build);
        scheduler.start();
        return "ok";
    }

    @GetMapping("/pause")
    @ResponseBody
    public String pause(@RequestParam("jobId") String jobId) throws SchedulerException {
        scheduler.pauseJob(JobKey.jobKey(jobId, "univ_job_group"));
        // 经验证，调用后不用重新调用start方法。
        return "ok";
    }

    @GetMapping("/resume")
    @ResponseBody
    public String resume(@RequestParam("jobId") String jobId) throws SchedulerException {
        scheduler.resumeJob(JobKey.jobKey(jobId, "univ_job_group"));
        // 经验证，调用后不用重新调用start方法。
        return "ok";
    }

    @GetMapping("/remove")
    @ResponseBody
    public String remove(@RequestParam("triggerId") String triggerId) throws SchedulerException {
        scheduler.unscheduleJob(TriggerKey.triggerKey(triggerId, "trigger_group"));
        // 经验证，调用后不用重新调用start方法。
        return "ok";
    }
}

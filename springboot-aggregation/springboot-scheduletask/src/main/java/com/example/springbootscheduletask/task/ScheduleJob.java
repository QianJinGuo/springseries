/*
 * Copyright: 2020 jinguo.tech Inc. All rights reserved.
 * 注意：本内容所有权归属于jinguo.tech 未征得作者同意情况下:
 * 不得擅自将其用于商业或其他环境，否则将会承担相应的法律责任。
 *
 * @projectName springboot-aggregation
 * @packageName com.example.springbootschedule
 * @fileName ScheduleJob
 * @author jinguo
 * @email felix@jinguo.tech
 * @date 2021-01-19 22:37:50
 *
 */
package com.example.springbootscheduletask.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author jinguo
 * @fileName ScheduleJob
 * @description 定时任务
 * @email felix@jinguo.tech
 * @date 2021-01-19 22:37:50
 */
@Component
@Slf4j
@EnableScheduling
public class ScheduleJob {
    @Scheduled(cron = "0 0 1 * * ?")
    @PostConstruct //会立即执行一次
    public void doClean() {
        log.info("test3");
        log.info("test2");
    }
}
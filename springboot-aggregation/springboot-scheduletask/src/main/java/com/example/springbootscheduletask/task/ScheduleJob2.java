/*
 * Copyright: 2020 jinguo.tech Inc. All rights reserved.
 * 注意：本内容所有权归属于jinguo.tech 未征得作者同意情况下:
 * 不得擅自将其用于商业或其他环境，否则将会承担相应的法律责任。
 *
 * @projectName springboot-aggregation
 * @packageName com.example.springbootscheduletask.task
 * @fileName ScheduleJob2
 * @author jinguo
 * @email felix@jinguo.tech
 * @date 2021-01-20 00:28:18
 *
 */
package com.example.springbootscheduletask.task;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * @author jinguo
 * @fileName ScheduleJob2
 * @description TODO
 * @email felix@jinguo.tech
 * @date 2021-01-20 00:28:18
 */
@Component
@Slf4j
public class ScheduleJob2 implements InitializingBean {
    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("execute");
        ScheduledTaskRegistrar taskRegistrar = new ScheduledTaskRegistrar();
//        Trigger trigger = triggerContext -> {
//            //0 0 1 19/7 * ?   1:00 开始执行 每7天执行一次
//            String expression = MessageFormat.format("0 44 0 {0}/{1} * ?", LocalDate.now().getDayOfMonth(),1);
//            System.out.println("expression:"+expression);
//            CronTrigger trigger1 = new CronTrigger("* * * * * ?");
//            return trigger1.nextExecutionTime(triggerContext);
//        };
      //  taskRegistrar.addTriggerTask(new TestTask(), trigger);
        Date date = Date.from(LocalDateTime.now().plusSeconds(1).atZone(ZoneId.systemDefault()).toInstant());
        System.out.println(LocalDateTime.now());
        System.out.println(MessageFormat.format("{0} {1}/{2} * ?",DateUtils.getCron(date),LocalDate.now().getDayOfMonth(),7));
        taskRegistrar.addCronTask(new TestTask(),MessageFormat.format("{0} {1}/{2} * ?",DateUtils.getCron(date),LocalDate.now().getDayOfMonth(),7));
        taskRegistrar.afterPropertiesSet();
    }
}

class TestTask implements Runnable {
    @Override
    public void run() {
        System.out.println("TEST RUN");
    }
}
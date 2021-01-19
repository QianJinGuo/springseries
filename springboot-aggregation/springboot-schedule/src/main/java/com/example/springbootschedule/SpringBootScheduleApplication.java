/*
 * Copyright: 2020 jinguo.tech Inc. All rights reserved.
 * 注意：本内容所有权归属于jinguo.tech 未征得作者同意情况下:
 * 不得擅自将其用于商业或其他环境，否则将会承担相应的法律责任。
 *
 * @projectName springboot-aggregation
 * @packageName com.example.springbootschedule
 * @fileName SpringBootApplication
 * @author jinguo
 * @email felix@jinguo.tech
 * @date 2021-01-19 22:16:54
 *
 */
package com.example.springbootschedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * @author jinguo
 * @fileName SpringBootApplication
 * @description 启动类
 * @email felix@jinguo.tech
 * @date 2021-01-19 22:16:54
 */
@SpringBootApplication
@Slf4j
public class SpringBootScheduleApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootScheduleApplication.class, args);
        log.info("test22222");
    }
}

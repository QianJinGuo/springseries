/*
 * Copyright: 2020 jinguo.tech Inc. All rights reserved.
 * 注意：本内容所有权归属于jinguo.tech 未征得作者同意情况下:
 * 不得擅自将其用于商业或其他环境，否则将会承担相应的法律责任。
 *
 * @projectName springboot-aggregation
 * @packageName com.example.springbootscheduletask.task
 * @fileName DateUtils
 * @author jinguo
 * @email felix@jinguo.tech
 * @date 2021-01-20 01:03:00
 *
 */
package com.example.springbootscheduletask.task;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author jinguo
 * @fileName DateUtils
 * @description TODO
 * @email felix@jinguo.tech
 * @date 2021-01-20 01:03:00
 */
public class DateUtils {
    /***
     * convert Date to cron ,eg.  "0 06 10 15 1 ? 2014"
     * @param date  : 时间点
     * @return
     */
    public static String getCron(java.util.Date  date){
        String dateFormat="s mm HH ";
        return formatDateByPattern(date, dateFormat);
    }
    /***
     *  修改cron参数格式
     * @param date
     * @param dateFormat : e.g:yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String formatDateByPattern(Date date, String dateFormat){
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        String formatTimeStr = null;
        if (date != null) {
            formatTimeStr = sdf.format(date);
        }
        return formatTimeStr;
    }
}
package com.yun.util.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 格式化工具
 * @author yun
 * created_time 2019-07-12 13:36.
 */

public class FormatUtil {
    /**
     * 开始/结束时间
     * @param date
     * @param isStart
     * @return
     */
    public static Long startOrEndDate(Date date, boolean isStart) {
        SimpleDateFormat dfDay = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = dfDay.format(date);

        if (isStart) {
            dateStr += " 00:00:00";

        } else {
            dateStr += " 23:59:59";
        }

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Long sDate = null;
        try {
            sDate = df.parse(dateStr).getTime();

        } catch (ParseException e) {
            throw new CommonException(-1, "time格式错误");
        }

        return sDate;
    }

    /**
     * 开始/结束时间
     * @param dayStr
     * @param isStart
     * @return
     */
    public static Long startOrEndDateByDayStr(String dayStr, boolean isStart) {
        if (isStart) {
            dayStr += " 00:00:00";

        } else {
            dayStr += " 23:59:59";
        }

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Long sDate = null;
        try {
            sDate = df.parse(dayStr).getTime();

        } catch (ParseException e) {
            throw new CommonException(-1, "time格式错误");
        }

        return sDate;
    }

    /**
     * 获取启动/禁用的文本
     * @param enable
     * @return
     */
    public static String enableStr(Integer enable) {
        boolean isEb = false;

        if (enable != null && !enable.equals(0)) {
            isEb = true;
        }

        return isEb ? "启用" : "禁用";
    }
}

package com.yun.util.common;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 异常信息处理
 * @author yun
 * created_time 2020/3/19 16:21.
 */

public class ThrowableUtil {
    public static String getStack(Throwable e) {
        return new ThrowableUtil().getStackMessage(e);
    }

    /**
     * 获取堆栈详情
     * @param e
     * @return
     */
    public String getStackMessage(Throwable e) {
        StringWriter sw = null;
        PrintWriter pw = null;
        try {
            sw = new StringWriter();
            pw = new PrintWriter(sw);
            // 将出错的栈信息输出到printWriter中
            e.printStackTrace(pw);
            pw.flush();
            sw.flush();
        } finally {
            if (sw != null) {
                try {
                    sw.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (pw != null) {
                pw.close();
            }
        }
        return sw.toString();
    }
}

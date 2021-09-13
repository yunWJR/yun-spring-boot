package com.yun.util.common;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * @author yun
 * <p>
 * create_time  2021/9/13 14:08.
 */

public class BigDecimalUtil {
    /**
     * @return
     */
    public static BigDecimal wBd() {
        return new BigDecimal(wIt());
    }

    /**
     * @return
     */
    public static Integer wIt() {
        return 10000;
    }

    /**
     * @return
     */
    public static RoundingMode defaultRm() {
        return RoundingMode.UP;
    }

    /**
     * 转为纯数字
     * @param bd
     * @return
     */
    public static String bdToNum(BigDecimal bd, String defaultValue) {
        if (bd == null) {
            return defaultValue;
        }

        String sv = bd.stripTrailingZeros().toPlainString();
        return sv;
    }

    /**
     * @param bd
     * @param defaultValue
     * @return
     */
    public static String bdToJe(BigDecimal bd, String defaultValue) {
        if (bd == null) {
            return defaultValue;
        }

        DecimalFormat df = new DecimalFormat("#,###.00");
        String sv = df.format(bd);
        if (sv.startsWith(".")) {
            sv = "0" + sv;
        } else if (sv.startsWith("-.")) {
            sv = "-0" + sv.substring(1);
        }

        return sv;
    }
}

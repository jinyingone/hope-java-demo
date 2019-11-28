package fun.jinying;

import java.math.BigDecimal;

/**
 * @description: double类型常见异常
 * @author: sjy
 * @create: 2019-11-28 14:58
 **/
public class DoubleError {
    public static boolean doubleEqual(double d1, double d2) {
        return d1 == d2;
    }

    public static String parseDoubleToByteString(double d) {
        //整数部分：除以2，取出余数，商继续除以2，直到得到0为止，将取出的余数逆序
        long longVaue = Math.abs((long) d);
        StringBuilder longStringBuilder = new StringBuilder();
        while (longVaue != 0) {
            longStringBuilder.append(longVaue % 2);
            longVaue = (longVaue - longVaue % 2) / 2;
            if (longVaue == 0) {
                break;
            }
        }
        //小数部分：乘以2，然后取出整数部分，将剩下的小数部分继续乘以2，然后再取整数部分，一直取到小数部分为零为止。如果永远不为零，则按要求保留足够位数的小数，最后一位做0舍1入。将取出的整数顺序排列。
        double decimalValue = new BigDecimal(d).remainder(BigDecimal.ONE).doubleValue();
        StringBuilder decimalStringBuilder = new StringBuilder();
        int maxLength = 64;
        for (int i = 0; i < maxLength && decimalValue != 0; i++) {
            long longValue = (long) (decimalValue * 2);
            decimalStringBuilder.append(longValue);
            decimalValue = decimalValue * 2 - longValue;
        }


        System.out.println(new BigDecimal(d).toString() + " 整数部分长度：" + longStringBuilder.length() + "小数部分长度:" + decimalStringBuilder.length());
        System.out.println(longStringBuilder.reverse().append(".").append(decimalStringBuilder).toString());
        return "";
    }
}

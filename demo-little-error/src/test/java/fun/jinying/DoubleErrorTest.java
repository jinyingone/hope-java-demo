package fun.jinying;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class DoubleErrorTest {

    /**
     * 为什么 +0.1相等？
     * 0.1 转为二进制： .0001100...
     * 1.409882155675642E15二进制：101000000100100011111000101000100000110111111111010
     * <p>
     * +0.1 实际的二进制：101000000100100011111000101000100000110111111111010.0001100
     * 因为只能用52位，且首位的1不用存储，所以表示为：01000000100100011111000101000100000110111111111010.00
     * +0.1不起作用
     * <p>
     * int s = ((bits >> 63) == 0) ? 1 : -1;
     * int e = (int)((bits >> 52) & 0x7ffL);
     * long m = (e == 0) ?
     * (bits & 0xfffffffffffffL) << 1 :
     * (bits & 0xfffffffffffffL) | 0x10000000000000L;
     */
    @Test
    void doubleEqual() {
        boolean b = DoubleError.doubleEqual(1.409882155675642E15, 1.409882155675642E15 + 0.1);
        System.out.println(b);
        assertTrue(b);
    }

    @Test
    void parseDoubleToByteString() {
        double v = new BigDecimal(1409882155675642D).add(new BigDecimal(0.1)).doubleValue();
        System.out.println(v);
        DoubleError.parseDoubleToByteString(0.1D);
        DoubleError.parseDoubleToByteString(1409882155675642.1D);

        DoubleError.parseDoubleToByteString(.2D);
        DoubleError.parseDoubleToByteString(1409882155675642.2D);
    }
}
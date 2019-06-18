package fun.jinying.demo;

import fun.jinying.demo.encrypt.DESDemo;
import org.junit.Assert;
import org.junit.Test;

/**
 * Des测试
 *
 * @author jy
 * @date 2019-06-18 下午5:41
 **/
public class DesTest {
    @Test
    public void testEnc() {
        System.out.println(new String(DESDemo.encrypt("1")));
        System.out.println(new String(DESDemo.encAndBase64("1")));
    }

    @Test
    public void testDsc() {
        String content = "123456";
        Assert.assertEquals(content, DESDemo.decrypt(DESDemo.encrypt(content)));
    }
}

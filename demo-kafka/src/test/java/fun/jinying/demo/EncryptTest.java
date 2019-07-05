package fun.jinying.demo;

import fun.jinying.demo.encrypt.AESDemo;
import fun.jinying.demo.encrypt.DESDemo;
import fun.jinying.demo.encrypt.RSADemo;
import org.junit.Assert;
import org.junit.Test;

/**
 * Des测试
 *
 * @author jy
 * @date 2019-06-18 下午5:41
 **/
public class EncryptTest {
    @Test
    public void testEnc() {
        String desKey = "12345678";
        Assert.assertEquals(DESDemo.encAndBase64(desKey, "123"), "p+IxjX3QiYw=");
    }

    @Test
    public void testDsc() {
        String desKey = "12345678";
        String content = "123456";
        Assert.assertEquals(content, DESDemo.decrypt(desKey, DESDemo.encrypt(desKey, content)));
        Assert.assertEquals(content, DESDemo.decBase64(desKey, DESDemo.encAndBase64(desKey, content)));
    }

    @Test
    public void testAESEnc() {
        String key = "0123456789abcdef";
        System.out.println(AESDemo.encrypt(key, "123"));
    }

    @Test
    public void testAESDec() {
        String key = "0123456789abcdef";
        Assert.assertEquals(AESDemo.decrypt(key, "Q0BNhRPR8AwNqhVldAS4aA=="), "123");
    }

    @Test
    public void testRSA() {
        RSADemo.KeyPairString keyPair = RSADemo.getKeyPair();
        Assert.assertEquals(RSADemo.decrypt(keyPair.getPrivateKey()
                , RSADemo.encrypt(keyPair.getPublicKey(), "123")), "123");
    }
}

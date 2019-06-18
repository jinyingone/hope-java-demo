package fun.jinying.demo.encrypt;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * Des加解密demo
 *
 * @author jy
 * @date 2019-06-18 下午5:13
 **/
public class DESDemo {
    public static final String DES_KEY = "12345678";

    /**
     * 加密字符串
     *
     * @param content
     * @return
     */
    public static byte[] encrypt(String content) {
        try {
            SecureRandom secureRandom = new SecureRandom();
            DESKeySpec desKeySpec = new DESKeySpec(DES_KEY.getBytes());
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey secretKey = secretKeyFactory.generateSecret(desKeySpec);
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, secureRandom);
            byte[] bytes = cipher.doFinal(content.getBytes());
            return bytes;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String encAndBase64(String content) {
        return new String(Base64.getEncoder().encode(encrypt(content)));
    }

    /**
     * 解密
     *
     * @param content
     * @return
     */
    public static String decrypt(byte[] content) {
        try {
            SecureRandom secureRandom = new SecureRandom();
            DESKeySpec desKeySpec = new DESKeySpec(DES_KEY.getBytes());
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey secretKey = secretKeyFactory.generateSecret(desKeySpec);
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey, secureRandom);
            byte[] bytes = cipher.doFinal(content);
            return new String(bytes);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

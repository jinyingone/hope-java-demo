package fun.jinying.demo.encrypt;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * Des加解密demo
 *
 * @author jy
 * @date 2019-06-18 下午5:13
 **/
public class DESDemo {
    /**
     * 加解密算法名称
     */
    public static final String ALGORITHM = "DES";
    /**
     * 参考文档http://www.blogjava.net/wayne/archive/2011/05/23/350879.html
     */
    public static final String TANSFORM_DES_EBC_PKCS5PADDING = "DES/ECB/PKCS5Padding";
    public static final String TANSFORM_DES_CFB_PKCS5PADDING = "DES/CFB/PKCS5Padding";

    /**
     * 加密字符串
     *
     * @param content
     * @return
     */
    public static byte[] encrypt(String desKey, String content) {
        try {
            SecureRandom secureRandom = new SecureRandom();
            DESKeySpec desKeySpec = new DESKeySpec(desKey.getBytes());
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(ALGORITHM);
            SecretKey secretKey = secretKeyFactory.generateSecret(desKeySpec);
            Cipher cipher = Cipher.getInstance(TANSFORM_DES_CFB_PKCS5PADDING);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, new IvParameterSpec(desKey.getBytes()),secureRandom);
            byte[] bytes = cipher.doFinal(content.getBytes());
            return bytes;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 加密并base64编码
     * 进行传输时,需要进行编码
     *
     * @param content
     * @return
     */
    public static String encAndBase64(String desKey, String content) {
        return Base64.getEncoder().encodeToString(encrypt(desKey, content));
    }

    /**
     * 解密
     *
     * @param content
     * @return
     */
    public static String decrypt(String desKey, byte[] content) {
        try {
            DESKeySpec desKeySpec = new DESKeySpec(desKey.getBytes());
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(ALGORITHM);
            SecretKey secretKey = secretKeyFactory.generateSecret(desKeySpec);
            Cipher cipher = Cipher.getInstance(TANSFORM_DES_CFB_PKCS5PADDING);
            cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(desKey.getBytes()));
            byte[] bytes = cipher.doFinal(content);
            return new String(bytes);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 解密base64后的内容
     *
     * @param content
     * @param desKey
     * @return
     */
    public static String decBase64(String desKey, String content) {
        return decrypt(desKey, Base64.getDecoder().decode(content.getBytes()));
    }
}

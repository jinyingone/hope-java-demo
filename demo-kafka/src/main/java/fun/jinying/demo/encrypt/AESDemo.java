package fun.jinying.demo.encrypt;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

/**
 * AES加解密demo
 *
 * @author jy
 * @date 2019-06-19 下午2:17
 **/
public class AESDemo {
    public static final String ALGORITHM = "AES";
    public static final int KEY_SIZE = 128;
    public static final String TANSFORM_AES_CFB_PKCS5PADDING = "AES/CFB/PKCS5Padding";

    public static String encrypt(String key, String input) {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), ALGORITHM);
            Cipher cipher = Cipher.getInstance(TANSFORM_AES_CFB_PKCS5PADDING);
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, new IvParameterSpec(key.getBytes()));
            byte[] bytes = cipher.doFinal(input.getBytes());
            return Base64.getEncoder().encodeToString(bytes);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String decrypt(String key, String input) {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), ALGORITHM);
            Cipher cipher = Cipher.getInstance(TANSFORM_AES_CFB_PKCS5PADDING);
            cipher.init(Cipher.DECRYPT_MODE, keySpec, new IvParameterSpec(key.getBytes()));
            byte[] decode = Base64.getDecoder().decode(input);
            byte[] bytes = cipher.doFinal(decode);
            return new String(bytes);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

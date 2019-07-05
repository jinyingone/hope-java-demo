package fun.jinying.demo.encrypt;

import sun.security.rsa.RSAKeyFactory;
import sun.security.rsa.RSAPrivateCrtKeyImpl;
import sun.security.rsa.RSAPrivateKeyImpl;
import sun.security.rsa.RSAPublicKeyImpl;

import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Base64;

/**
 * RSA 加解密算法
 *
 * @author jy
 * @date 2019-07-05 上午11:04
 **/
public class RSADemo {
    public static final String ALGORITHM = "RSA";
    public static final String TANSFORM_RSA_CFB_PKCS5PADDING = "RSA/ECB/PKCS1Padding";
    //最小值512
    public static final int KEY_SIZE = 512;

    public static String encrypt(PublicKey publicKey, String input) {
        try {
            Cipher cipher = Cipher.getInstance(TANSFORM_RSA_CFB_PKCS5PADDING);
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(input.getBytes()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String encrypt(String publicKey, String input) {
        try {
            RSAPublicKey rsaPublicKey = new RSAPublicKeyImpl(Base64.getDecoder().decode(publicKey));
            Cipher cipher = Cipher.getInstance(TANSFORM_RSA_CFB_PKCS5PADDING);
            cipher.init(Cipher.ENCRYPT_MODE, rsaPublicKey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(input.getBytes()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String decrypt(String privateKey, String input) {
        try {
            RSAPrivateKey rsaPrivateKey = RSAPrivateCrtKeyImpl.newKey(Base64.getDecoder().decode(privateKey));
            Cipher cipher = Cipher.getInstance(TANSFORM_RSA_CFB_PKCS5PADDING);
            cipher.init(Cipher.DECRYPT_MODE, rsaPrivateKey);
            return new String(cipher.doFinal(Base64.getDecoder().decode(input.getBytes(StandardCharsets.UTF_8))));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static KeyPair genKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator kpg = KeyPairGenerator.getInstance(ALGORITHM);
        kpg.initialize(KEY_SIZE, new SecureRandom());
        java.security.KeyPair keyPair = kpg.genKeyPair();
        return keyPair;
    }


    public static void main(String[] args) throws NoSuchAlgorithmException {
        KeyPair keyPair = genKeyPair();
        String encrypt = encrypt(Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded()), "123456");
        System.out.println(encrypt);
        System.out.println(decrypt(Base64.getEncoder().encodeToString(keyPair.getPrivate().getEncoded()), encrypt));
    }
}

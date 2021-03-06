package fun.jinying.demo.encrypt;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
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

    private static String encrypt(PublicKey publicKey, String input) {
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
            RSAPublicKey rsaPublicKey = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(
                    new X509EncodedKeySpec(Base64.getDecoder().decode(publicKey)));
            Cipher cipher = Cipher.getInstance(TANSFORM_RSA_CFB_PKCS5PADDING);
            cipher.init(Cipher.ENCRYPT_MODE, rsaPublicKey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(input.getBytes()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String decrypt(String privateKey, String input) {
        try {
            RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) KeyFactory.getInstance("RSA").generatePrivate(
                    new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKey)));
            Cipher cipher = Cipher.getInstance(TANSFORM_RSA_CFB_PKCS5PADDING);
            cipher.init(Cipher.DECRYPT_MODE, rsaPrivateKey);
            return new String(cipher.doFinal(Base64.getDecoder().decode(input.getBytes(StandardCharsets.UTF_8))));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static KeyPair genKeyPair() {
        try {
            KeyPairGenerator kpg = KeyPairGenerator.getInstance(ALGORITHM);
            kpg.initialize(KEY_SIZE, new SecureRandom());
            KeyPair keyPair = kpg.genKeyPair();
            return keyPair;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static KeyPairString getKeyPair() {
        KeyPair keyPair = genKeyPair();
        return new KeyPairString(Base64.getEncoder().encodeToString(keyPair.getPrivate().getEncoded()),
                Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded()));
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class KeyPairString {
        private String privateKey;
        private String publicKey;
    }

}

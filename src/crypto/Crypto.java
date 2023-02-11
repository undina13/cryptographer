package crypto;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Crypto {

    private static final Logger logger = Logger.getLogger(Crypto.class.getName());
    private String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC6x8kPy/rIm8t/kqIfK+a9ceUpKeqDWeKtnO5jqxLVesUOVsQBXelj5BSw7Eu01jvHIDGvRayretDLpHRBsmAvMtYta/w9FgTBcB6GsEcqhvVM5MeUsQPHcrHFeNU21DqrHxZwNOTJbyUf9FbGbB+7Ey9/BSMQkku6bdg7SM/56wIDAQAB";
    private String privateKey = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBALrHyQ/L+siby3+Soh8r5r1x5Skp6oNZ4q2c7mOrEtV6xQ5WxAFd6WPkFLDsS7TWO8cgMa9FrKt60MukdEGyYC8y1i1r/D0WBMFwHoawRyqG9Uzkx5SxA8dyscV41TbUOqsfFnA05MlvJR/0VsZsH7sTL38FIxCSS7pt2DtIz/nrAgMBAAECgYALTZfPSurMRRFVkQwaks+N21yg1xaICGJ9WyfaBDpJjPpr2bhW1NYQ43aVFlemg+huLnaTQsYmQnJsQHGAoEjNt+Mls7LeghcSZcJhuJn3tts4g/eWDZol2r0NmMU9raf00VMv2DEUYl1skpFinruxiTNJv7paak0thd9TTdgs0QJBAOjdOokQs5TcAgPiwRsBHQOZuoFKrHaodER9s3uiiaryOj64JhH/uFU/+GlFxA6NwASBCfbgJ2Y0pHr2ocrTbl8CQQDNVnEWx8OJUcTrmiztNYII6b+gBytIKDzKGCniKZxSGe9wLrTPkT7WY70J174QvLHNLQpbEVQFxkg6msc/BUf1AkAULa931yGooZPvIEQZgfQwrq6Rq5XcRmak1Lur1/7T+F+BtdVes+kWQg/Vl30QwcnE2iWx5eSkH6WtHhgRCldDAkAz2PPNMLuq3L7ATrmxW6Vt4rfEDItdOOvAi8FnneIeyPWwedql3qLjN2PwLE2NpFew2wKWZBW3L43lXleZDhhRAkAHT2HiEo9W31ErPxK4PHcda6dKEG08Vr65vWWqZCXuO/zezDzp2FF4b8c1DG7yxZGvCwapW78q/PrIiroFl9A3";
    private Cipher cipher;
    private KeyFactory keyFactory;

    public Crypto() {
        try {
            this.cipher = Cipher.getInstance("RSA");
            this.keyFactory = KeyFactory.getInstance("RSA");
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, InvalidKeySpecException, BadPaddingException, InvalidKeyException {
        Crypto crypto = new Crypto();

        String s = crypto.encrypt("privet");
        crypto.decrypt(s);

    }

    private void createKeys() throws NoSuchAlgorithmException {
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(2048);
        KeyPair keyPair = generator.generateKeyPair();
        String privateKey = Base64.getEncoder().encodeToString(keyPair.getPrivate().getEncoded());
        logger.log(Level.INFO, String.format("%s   private", privateKey));
        String publicKey = Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded());
        logger.log(Level.INFO, String.format("%s   public", publicKey));
    }

    public String encrypt(String password) throws InvalidKeySpecException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        byte[] publicBytes = Base64.getDecoder().decode(publicKey);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicBytes);
        PublicKey pubKey = keyFactory.generatePublic(keySpec);
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);
        byte[] data = cipher.doFinal(password.getBytes());
        return Base64.getEncoder().encodeToString(data);
    }

    public String decrypt(String password) throws InvalidKeySpecException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        byte[] privateBytes = Base64.getDecoder().decode(privateKey);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateBytes);
        PrivateKey privKey = keyFactory.generatePrivate(keySpec);
        cipher.init(Cipher.DECRYPT_MODE, privKey);
        return new String(cipher.doFinal(Base64.getDecoder().decode(password)));
    }
}

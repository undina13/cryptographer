import crypto.Crypto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;

/**
 * тесты корректно работают с ключами
 * private String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC6x8kPy/rIm8t/kqIfK+a9ceUpKeqDWeKtnO5jqxLVesUOVsQBXelj5BSw7Eu01jvHIDGvRayretDLpHRBsmAvMtYta/w9FgTBcB6GsEcqhvVM5MeUsQPHcrHFeNU21DqrHxZwNOTJbyUf9FbGbB+7Ey9/BSMQkku6bdg7SM/56wIDAQAB";
 * private String privateKey = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBALrHyQ/L+siby3+Soh8r5r1x5Skp6oNZ4q2c7mOrEtV6xQ5WxAFd6WPkFLDsS7TWO8cgMa9FrKt60MukdEGyYC8y1i1r/D0WBMFwHoawRyqG9Uzkx5SxA8dyscV41TbUOqsfFnA05MlvJR/0VsZsH7sTL38FIxCSS7pt2DtIz/nrAgMBAAECgYALTZfPSurMRRFVkQwaks+N21yg1xaICGJ9WyfaBDpJjPpr2bhW1NYQ43aVFlemg+huLnaTQsYmQnJsQHGAoEjNt+Mls7LeghcSZcJhuJn3tts4g/eWDZol2r0NmMU9raf00VMv2DEUYl1skpFinruxiTNJv7paak0thd9TTdgs0QJBAOjdOokQs5TcAgPiwRsBHQOZuoFKrHaodER9s3uiiaryOj64JhH/uFU/+GlFxA6NwASBCfbgJ2Y0pHr2ocrTbl8CQQDNVnEWx8OJUcTrmiztNYII6b+gBytIKDzKGCniKZxSGe9wLrTPkT7WY70J174QvLHNLQpbEVQFxkg6msc/BUf1AkAULa931yGooZPvIEQZgfQwrq6Rq5XcRmak1Lur1/7T+F+BtdVes+kWQg/Vl30QwcnE2iWx5eSkH6WtHhgRCldDAkAz2PPNMLuq3L7ATrmxW6Vt4rfEDItdOOvAi8FnneIeyPWwedql3qLjN2PwLE2NpFew2wKWZBW3L43lXleZDhhRAkAHT2HiEo9W31ErPxK4PHcda6dKEG08Vr65vWWqZCXuO/zezDzp2FF4b8c1DG7yxZGvCwapW78q/PrIiroFl9A3";
 */

 class CryptoTest {
     public static int ENCRYPTED_STRING_LENGTH = 172;

    @Test
    void encryptTest() throws IllegalBlockSizeException, InvalidKeySpecException, BadPaddingException, InvalidKeyException {
        Crypto crypto = new Crypto();
        String cryptoPassword = crypto.encrypt("new text password");
        Assertions.assertEquals(cryptoPassword.length(), ENCRYPTED_STRING_LENGTH);
        String password = crypto.decrypt(cryptoPassword);
        Assertions.assertEquals("new text password", password);
    }

    @Test
    void decryptTest() throws IllegalBlockSizeException, InvalidKeySpecException, BadPaddingException, InvalidKeyException {
        Crypto crypto = new Crypto();
        String password = crypto.decrypt("rkSMNTgzHQORVL72XEG4rP/uX2tn6Up/ma9Z/wKL2vFPgUPA14SFTnSH1hGKvD7RT1v62+yjS6KRu1243kmWhzVAoKKq+HMwnMiJrQs++N4zOyY16WqyojLmezVCChOX1Elqe80q2+mQoeWHkLKlMN8zwOAvhlkDpyVMoi4at+Q=");
        Assertions.assertEquals("new text password", password);
    }

    @Test
    void decryptTestRandomCrypto() throws IllegalBlockSizeException, InvalidKeySpecException, BadPaddingException, InvalidKeyException {
        Crypto crypto = new Crypto();
        for (int i = 0; i < 10; i++) {
            String cryptoPassword = crypto.encrypt("string, new string");
            Assertions.assertEquals(cryptoPassword.length(), 172);
            String password = crypto.decrypt(cryptoPassword);
            Assertions.assertEquals("string, new string", password);
        }
    }
}

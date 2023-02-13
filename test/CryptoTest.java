import crypto.Crypto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;


 class CryptoTest {
    @Test
    void encryptTest() throws IllegalBlockSizeException, InvalidKeySpecException, BadPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, NoSuchPaddingException, NoSuchAlgorithmException {
        Crypto crypto = new Crypto();
        String cryptoPassword = crypto.encrypt("new text password");
        System.out.println(cryptoPassword);
        String password = crypto.decrypt(cryptoPassword);
        Assertions.assertEquals("new text password", password);
    }

    @Test
    void decryptTest() throws IllegalBlockSizeException, InvalidKeySpecException, BadPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, NoSuchPaddingException, NoSuchAlgorithmException {
        Crypto crypto = new Crypto();
        String password = crypto.decrypt("7Gd0EB4FRVi0ze5FMpcLJ0Wme7StRYiKw+tPorCTdss=");
        Assertions.assertEquals("new text password", password);
    }
}

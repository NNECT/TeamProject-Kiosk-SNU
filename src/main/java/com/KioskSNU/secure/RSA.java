package com.KioskSNU.secure;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

@Component
@SessionScope
public class RSA {
    private final PrivateKey privateKey;
    private final String publicKey;

    public RSA() throws NoSuchAlgorithmException {
        SecureRandom sr = new SecureRandom();
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048, sr);
        KeyPair kp = keyGen.genKeyPair();

        this.privateKey = kp.getPrivate();
        this.publicKey = Base64.getEncoder().encodeToString(kp.getPublic().getEncoded());
    }

    public String getPublicKey() {
        return publicKey;
    }

    public String decrypt(String text) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);

        byte[] bytesE = Base64.getDecoder().decode(text.getBytes());
        byte[] bytesD = cipher.doFinal(bytesE);

        return new String(bytesD);
    }

    public String encrypt(String text) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        KeyFactory kf = KeyFactory.getInstance("RSA");
        byte[] bytesP = Base64.getDecoder().decode(publicKey.getBytes());
        X509EncodedKeySpec spec = new X509EncodedKeySpec(bytesP);
        PublicKey publicKey = kf.generatePublic(spec);

        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);

        byte[] bytesE = cipher.doFinal(text.getBytes());

        return Base64.getEncoder().encodeToString(bytesE);
    }
}

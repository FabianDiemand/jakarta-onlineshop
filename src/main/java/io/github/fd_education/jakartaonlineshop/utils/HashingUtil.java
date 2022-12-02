package io.github.fd_education.jakartaonlineshop.utils;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.logging.Logger;

public class HashingUtil {

    private final static Logger log = Logger
            .getLogger(HashingUtil.class.toString());

    public static byte[] getHash(String value){
        SecureRandom rnd = new SecureRandom();
        byte[] salt = new byte[16];

        rnd.nextBytes(salt);

        try{
            KeySpec spec = new PBEKeySpec(value.toCharArray(), salt, 65536, 128);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");

            return factory.generateSecret(spec).getEncoded();
        } catch(NoSuchAlgorithmException ex){
            log.severe("The algorithm specified is not provided!");
        } catch(InvalidKeySpecException ex){
            log.severe("The keyspec is invalid!");
        }

        return null;
    }
}

package com.shopping.encryption;

import java.math.BigInteger; 
import java.security.MessageDigest; 
import java.security.NoSuchAlgorithmException;
 
import com.shopping.exception.ShopException;

public class PasswordEncrypt {
 
    public static String getEncryptedPassword(String password) throws ShopException { 
        try { 
            MessageDigest encrypt = MessageDigest.getInstance("SHA-256"); 
            byte[] messageDigest = encrypt.digest(password.getBytes()); 
            BigInteger bigInteger = new BigInteger(1, messageDigest); 
            String hashtext = bigInteger.toString(16); 
            while (hashtext.length() < 32) { 
                hashtext = "0" + hashtext; 
            } 
            return hashtext; 
        } catch (NoSuchAlgorithmException e) { 
            throw new ShopException("Algorithm failure"); 
        } 
    } 
} 


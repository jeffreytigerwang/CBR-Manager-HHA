package com.example.cbr.retrofit;

import android.util.Base64;

import java.security.SecureRandom;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AES {

    private static final String AES_ALGORITHM = "AES/CBC/PKCS5Padding";
    private static final int SIZE_BYTE_ARRAY = 16;
    private static final String ENCODE_FORMAT = "utf-8";
    private static final String AES = "AES";

    private final static String TOKEN_KEY = "fqJfdzGDvfwbedsKSUGty3VZ9taXxMVw";

    public static String encrypt(String plain) {
        try {
            byte[] iv = new byte[SIZE_BYTE_ARRAY];
            new SecureRandom().nextBytes(iv);
            Cipher cipher = Cipher.getInstance(AES_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(TOKEN_KEY.getBytes(ENCODE_FORMAT), AES), new IvParameterSpec(iv));
            byte[] cipherText = cipher.doFinal(plain.getBytes(ENCODE_FORMAT));
            byte[] ivAndCipherText = getCombinedArray(iv, cipherText);
            return Base64.encodeToString(ivAndCipherText, Base64.NO_WRAP);
        } catch (Exception e) {
            return null;
        }
    }

    public static String decrypt(String encoded) {
        try {
            byte[] ivAndCipherText = Base64.decode(encoded, Base64.NO_WRAP);
            byte[] iv = Arrays.copyOfRange(ivAndCipherText, 0, SIZE_BYTE_ARRAY);
            byte[] cipherText = Arrays.copyOfRange(ivAndCipherText, SIZE_BYTE_ARRAY, ivAndCipherText.length);

            Cipher cipher = Cipher.getInstance(AES_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(TOKEN_KEY.getBytes(ENCODE_FORMAT), AES), new IvParameterSpec(iv));
            return new String(cipher.doFinal(cipherText), ENCODE_FORMAT);
        } catch (Exception e) {
            System.out.println("Error while decrypting: " + e.toString());
        }
        return null;
    }

    private static byte[] getCombinedArray(byte[] one, byte[] two) {
        byte[] combined = new byte[one.length + two.length];
        for (int i = 0; i < combined.length; ++i) {
            combined[i] = i < one.length ? one[i] : two[i - one.length];
        }
        return combined;
    }

}

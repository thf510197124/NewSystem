package com.taiquan.utils;


import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import java.security.Key;
import java.security.SecureRandom;

public class DESUtils {
    private static Key key;
    private static String KEY_STR = "newSteelSystem";
    static {


        try{
            KeyGenerator generator = KeyGenerator.getInstance("DES");
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(KEY_STR.getBytes());
            generator.init(secureRandom);
            key = generator.generateKey();
            generator = null;

            /*SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            DESKeySpec keySpec = new DESKeySpec(KEY_STR.getBytes("utf-8"));
            keyFactory.generateSecret(keySpec);
            key = keyFactory.generateSecret(keySpec);*/

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    //加密
    public static String getEncryptString(String str){
        //BASE64Encoder base64Encoder = new BASE64Encoder();
        try {
            byte[] strBytes = str.getBytes("UTF8");
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.ENCRYPT_MODE,key);
            byte[] encryptStrBytes = cipher.doFinal(strBytes);
            //return base64Encoder.encode(encryptStrBytes);
            return Base64.encodeBase64String(encryptStrBytes);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    //解密
    public static String getDecryptString(String str){
        //BASE64Decoder base64Decoder = new BASE64Decoder();
        try {
            //byte[] strBytes = base64Decoder.decodeBuffer(str);
            byte[] strBytes = Base64.decodeBase64(str);
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.DECRYPT_MODE,key);
            byte[] decryptStrBytes = cipher.doFinal(strBytes);
            return new String(decryptStrBytes,"UTF8");
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    public static void main(String[] args){
        System.out.println(getEncryptString("8023myself"));
    }
}

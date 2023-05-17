package com.ssafy.trip.util;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
public class OpenCrypt {
	
	  public static byte[] generateKey(String algorithm,int keySize) throws NoSuchAlgorithmException {
			 
	       KeyGenerator keyGenerator = KeyGenerator.getInstance(algorithm);	 
	       keyGenerator.init(keySize);
	       SecretKey key = keyGenerator.generateKey();
	       return key.getEncoded();	 
}	
	  
	public static String aesDecrypt(String msg,byte[] key ) throws Exception {
	        SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        String iv = "AAAAAAAAAAAAAAAA";
        cipher.init(Cipher.DECRYPT_MODE, 
        		       skeySpec,
        		       new IvParameterSpec(iv.getBytes()));  
        byte[] encrypted = hexToByteArray(msg);
        byte[] original = cipher.doFinal(encrypted);  
        return new String(original); 
}

	
	public static String aesEncript(String msg,byte[] key) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
	    SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		String iv = "AAAAAAAAAAAAAAAA";
		cipher.init(Cipher.ENCRYPT_MODE, skeySpec, new IvParameterSpec(iv.getBytes()));
        byte[] encrypted = cipher.doFinal(msg.getBytes());     
        return  byteArrayToHex(encrypted);
	}
	public static String getSHA256(String source,String salt) throws Exception {
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		md.update(source.getBytes());
		md.update(salt.getBytes());
		byte[] byteData = md.digest();
		String hashMsg = byteArrayToHex(byteData);
		System.out.println(byteData);
		return hashMsg;
	}
	// byte[] to hex
	public static String byteArrayToHex(byte[] ba) {
	    if (ba == null || ba.length == 0) {
	        return null;
	    }
	 
	    StringBuffer sb = new StringBuffer(ba.length * 2);
	    String hexNumber;
	    for (int x = 0; x < ba.length; x++) {
	        hexNumber = "0" + Integer.toHexString(0xff & ba[x]);
	 
	        sb.append(hexNumber.substring(hexNumber.length() - 2));
	    }
	    return sb.toString();
	} 
	
	 public static byte[] hexToByteArray(String hex) {
		    if (hex == null || hex.length() == 0) {
		        return null;
		    }
		 
		    byte[] ba = new byte[hex.length() / 2];
		    for (int i = 0; i < ba.length; i++) {
		        ba[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
		    }
		    return ba;
		}
		 

}

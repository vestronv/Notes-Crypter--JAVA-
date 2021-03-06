package com.crypter;

import java.security.Security;
import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.*;
import javax.swing.JOptionPane;
import com.gui.*;
//import org.apache.commons.codec.binary.*;
//import org.apache.commons.codec.binary.Base64;

public class EnDecryptMsg {
	
	static String encryptionKey = "0123456789abcdef";
	static String IV = "VIMALSINGHTWENTY";
	static String secretKey = variables.secret_key;	

	public EnDecryptMsg() {	
	}
	/**
	public static void main(String[] args){
		String msg = "vimal >\"<";
		System.out.println("Original : "+msg+"\nEncrypted : "+crypt(msg,secretKey));
		System.out.println("Decrypted : "+decrypt(crypt(msg,secretKey),secretKey));
	}
	**/
	public static String crypt(String plainText) {
		//plainText = plainText.replace("\"","\\"");		
		StringBuffer encryptedString = new StringBuffer();
		int encryptedInt;
		int secretKeyLen = secretKey.length();		
		for (int i = 0,j = 0; i < plainText.length(); i++) {
			if(j>=secretKeyLen)j=0;			
			int plainTextInt = (int) (plainText.charAt(i) - secretKey.charAt(j));
			j++;
			//int secretKeyInt = (int) (secretKey.charAt(i) - 'A');
			//encryptedInt = (plainTextInt + 17) % 26;
			encryptedInt = plainTextInt;
			encryptedString.append((char) ((encryptedInt)+(int) 'A'-(int) 'A'));
		}
		return encryptedString.toString();
	}
	
	public static String decrypt(String decryptedText) {
		StringBuffer decryptedString = new StringBuffer();
		int decryptedInt;
		int secretKeyLen = secretKey.length();
		for (int i = 0,j = 0; i < decryptedText.length(); i++) {
			if(j>=secretKeyLen)j=0;			
			int decryptedTextInt = (int) (decryptedText.charAt(i) + secretKey.charAt(j));
			j++;
			//int secretKeyInt = (int) (secretKey.charAt(i) - 'A');
			//decryptedInt = decryptedTextInt - 17;
			//if (decryptedInt < 1)
			//	decryptedInt += 26;
			decryptedInt = decryptedTextInt;
			decryptedString.append((char) ((decryptedInt) + (int) 'A'-(int) 'A'));
		}
		return decryptedString.toString();
	}

/**
	public static String crypt(String plaintext){
		byte[] cipher = null;
		try{
			cipher = encrypt(plaintext, encryptionKey);
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null, e);
		}
		return cipher.toString();
	}
	public static String decrypt(String cipherText){
		String plaintext = "";
		try{
			plaintext = decrypt(cipherText.getBytes(), encryptionKey);
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null, e);
		}
		return plaintext;
	}
	
	public static byte[] encrypt(String plainText, String encryptionKey) throws Exception {
		Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding", "SunJCE");
		SecretKeySpec key = new SecretKeySpec(encryptionKey.getBytes("UTF-8"), "AES");
		cipher.init(Cipher.ENCRYPT_MODE, key,new IvParameterSpec(IV.getBytes("UTF-8")));
		return cipher.doFinal(plainText.getBytes("UTF-8"));
	}

	public static String decrypt(byte[] cipherText, String encryptionKey) throws Exception{
		Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding", "SunJCE");
		SecretKeySpec key = new SecretKeySpec(encryptionKey.getBytes("UTF-8"), "AES");
		cipher.init(Cipher.DECRYPT_MODE, key,new IvParameterSpec(IV.getBytes("UTF-8")));
		return new String(cipher.doFinal(cipherText),"UTF-8");
	}
	**/
	
	/**
	 public static String symmetricEncrypt(String text, String secretKey) {
         byte[] raw;
         String encryptedString;
         SecretKeySpec skeySpec;
         byte[] encryptText = text.getBytes();
         Cipher cipher;
         try {
             raw = Base64.decodeBase64(secretKey);
             skeySpec = new SecretKeySpec(raw, "AES");
             cipher = Cipher.getInstance("AES");
             cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
             encryptedString = Base64.encodeBase64String(cipher.doFinal(encryptText));
         } 
         catch (Exception e) {
             e.printStackTrace();
             return "Error";
         }
         return encryptedString;
     }**/
/**
     public static String symmetricDecrypt(String text, String secretKey) {
         Cipher cipher;
         String encryptedString;
         byte[] encryptText = null;
         byte[] raw;
         SecretKeySpec skeySpec;
         try {
             raw = Base64.decodeBase64(secretKey);
             skeySpec = new SecretKeySpec(raw, "AES");
             encryptText = Base64.decodeBase64(text);
             cipher = Cipher.getInstance("AES");
             cipher.init(Cipher.DECRYPT_MODE, skeySpec);
             encryptedString = new String(cipher.doFinal(encryptText));
         } catch (Exception e) {
             e.printStackTrace();
             return "Error";
         }
         return encryptedString;
     }**/
/**
     public static void main(String[] args) {
         String secretKey = "XMzDdG4D03CKm2IxIWQw7g==";
         String value1= "ABCD";
         String enctypedValue1= "3uweh4pzoVyH1uODQmVNJA==";
         String enctypedValue2= "37PTC20w4DMZYjG3f+GWepSvAbEJUccMXwS/lXilLav1qM/PrCTdontw5/82OdC1zzyhDEsFVRGo rV6gXAQcm+Zai15hliiUQ8l8KRMtUl4=";
         String value4= "20000";

         //  Ecnryption and decryption of value1 
         String encryptedValue1= symmetricEncrypt(value1, secretKey);
         String decryptedValue1 = symmetricDecrypt(encryptedValue1, secretKey);
         System.out.println(decryptedValue1);

         //  Decryption of  enctypedValue1 
         String decryptedValue2 = symmetricDecrypt(enctypedValue1, secretKey);
         System.out.println(decryptedValue2);

         //  Decryption of  enctypedValue2 
         String decryptedValue3 = symmetricDecrypt(enctypedValue2, secretKey);
         System.out.println(decryptedValue3);

         //  Ecnryption and decryption of value4 
         String encryptedValue4= symmetricEncrypt(value4, secretKey);
         String decryptedValue4 = symmetricDecrypt(encryptedValue4, secretKey);
         System.out.println(decryptedValue4);
     }**/
	
	
	
	
}

package br.ufsc.labsec;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;

public class DetectAES {
	private static SecretKeySpec key;
	
	public static String detect(String text) throws DecoderException, NoSuchAlgorithmException, 
			NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		String[] split = text.split("\n");
		
		for (String string : split) {
//			System.out.println(HexToBase64Converter.convertToBase64(string));
//			System.out.println(string);
//			byte[] decodeBase64 = Base64.decodeBase64(HexToBase64Converter.convertToBase64(string));
			byte[] decodeBase64 = string.getBytes();
			try {
				for (int i = 0; i < decodeBase64.length; i++) {
					int z = 0;
					byte[] k = new byte[16];
					while(z < 16) {
						k[z] = decodeBase64[z];
						z++;
					}
					key = new SecretKeySpec(k, "AES");
					cipher.init(Cipher.DECRYPT_MODE, key);
					System.out.println(new String(cipher.doFinal(HexToBase64Converter.convertToBase64(string).getBytes())));
				}
			} catch (Exception e) {
				continue;
			}
//			System.out.println(new String(decodeBase64));
		}
		
		return "";
	}
}

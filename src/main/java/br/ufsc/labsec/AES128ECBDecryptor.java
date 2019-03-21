package br.ufsc.labsec;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.ShortBufferException;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

/**
 * 
 * @author Sadi
 *
 * Bastou usar APIs prontas do Java security e do Java crypto.
 * 
 * Além disso, o site oficial desse algoritmo (https://aesencryption.net/, para quem tem interesse), é
 * incrivelmente bem documentado, além de ter exemplos práticos (inclusive em Java!).
 */
public class AES128ECBDecryptor {
	private static SecretKeySpec key;

	public static String decryptBase64(String text, String k) throws NoSuchAlgorithmException, NoSuchPaddingException,
			IllegalBlockSizeException, ShortBufferException, BadPaddingException, InvalidKeyException {
		
		key = new SecretKeySpec(k.getBytes(), "AES");
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, key);
		System.out.println(new String(cipher.doFinal(Base64.decodeBase64(text.getBytes()))));
		return new String(cipher.doFinal(Base64.decodeBase64(text.getBytes()))).trim();
	}
}
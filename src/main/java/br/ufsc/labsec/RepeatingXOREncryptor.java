package br.ufsc.labsec;

import org.apache.commons.codec.binary.Hex;

/**
 * 
 * @author Sadi
 *
 * Bastou usar a função resto que deu tudo certo.
 */
public class RepeatingXOREncryptor {
	
	public static String encrypt(String text) {
		byte[] bytes = text.getBytes();
		char[] keys = new char[]{'I', 'C', 'E'};
		
		StringBuffer result = new StringBuffer();

		for (int i = 0; i < bytes.length; i++) {
			byte[] xorCombination = XORCombinationCryptography.xorCombination(new byte[]{bytes[i]}, (byte) keys[i % 3]);
			result.append(new String(xorCombination));
		}
		System.out.println(Hex.encodeHex(result.toString().getBytes()));
		return new String(Hex.encodeHex(result.toString().getBytes()));
	}
}
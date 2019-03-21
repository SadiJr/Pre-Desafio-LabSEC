package br.ufsc.labsec;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

/**
 * 
 * @author Sadi
 * 
 *         Devo admitir que levei um bom tempo para entender esse exercício. No
 *         final das contas, minha lógica estava certa, mas eu não percebi que
 *         era necessário decodificar as duas Strings inicias para Hex e, após
 *         todo o processamento, codificar novamente para Hex (culpo meu inglês
 *         por isso).
 *
 */
public class XORCombinationCryptography {

	public static String xorCombination(String message, String key) throws DecoderException {
		byte[] messageInByte = Hex.decodeHex(message.toCharArray());
		byte[] keyInByte = Hex.decodeHex(key.toCharArray());

		int max = Math.max(messageInByte.length, keyInByte.length);
		byte[] result = new byte[max];

		for (int i = 0; i < max; i++)
			result[i] = (byte) (messageInByte[i] ^ keyInByte[i % max]);
		return new String(Hex.encodeHex(result));
	}

	public static String xorCombination(String message, byte[] key) throws DecoderException {
		byte[] messageInByte = Hex.decodeHex(message.toCharArray());

		int max = Math.max(messageInByte.length, key.length);
		byte[] result = new byte[max];

		for (int i = 0; i < max; i++)
			result[i] = (byte) (messageInByte[i] ^ key[i]);
		return new String(Hex.encodeHex(result));
	}

	public static String xorCombination(String message, byte key) throws DecoderException {
		byte[] messageInByte = Hex.decodeHex(message.toCharArray());

		int max = messageInByte.length;
		byte[] result = new byte[max];

		for (int i = 0; i < max; i++)
			result[i] = (byte) (messageInByte[i] ^ key);
		return new String(Hex.encodeHex(result));
	}

	public static byte[] xorCombination(byte[] message, byte key) {
		byte[] result = new byte[message.length];
		for (int i = 0; i < message.length; i++) {
			result[i] = (byte) (message[i] ^ key);
		}
		return result;
	}
}
package br.ufsc.labsec;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

/**
 * 
 * @author Sadi
 * 
 * Levou um bom tempo para conseguir solucionar esse.
 * 
 * Basicamente, tive que testar todos os caracteres possíveis e verificar se, ao descriptografar a
 * mensagem usando o tal, se a mensagem descriptografada apresentava caracteres válidos e adicionar um
 * somátorio a cada hit de caracter válido.
 * 
 * Apesar de no site estar aconselhado a tentar verificar a existência de palavras válidas na língua inglesa,
 * e apesar de eu ter tentado isso a princípio, desisti e tentei apenas buscar caracteres válidos.
 * 
 * Caso tenha curiosidade, descomente a linha 39 e veja todas as possíveis keys.
 */
public class SingleXORCipher {

	public static Map<Character, Map<String, Double>> decryptMessage(String message) throws DecoderException, UnsupportedEncodingException {
		double maxFrequency = 0.0;
		String result = "";
		char key = ' ';
		for (char i = 0; i < 255; i++) {
			byte[] xorCombination = XORCombinationCryptography.xorCombination(Hex.decodeHex(message.toCharArray()), (byte) i);
			double frequency = frequency(xorCombination);
			
			if(frequency > maxFrequency) {
				maxFrequency = frequency;
				result = new String(xorCombination, "UTF-8");
				key = i;
//				System.out.println("Message: " + new String(xorCombination, "UTF-8") + " - Key: " + i);
			}
		}
		Map<Character, Map<String, Double>> cryptography = new HashMap<Character, Map<String, Double>>();
		Map<String, Double> messageAndScore = new HashMap<String, Double>();
		
		messageAndScore.put(result, maxFrequency);
		cryptography.put(key, messageAndScore);
		
		return cryptography;
	}
	
	public static int frequency(byte[] text) {
		int frequency = 0;
		for (byte b : text) {
			if (isUTFCharacter(b)) {
				frequency++;
			}
		}
		return frequency;
	}

	public static boolean isUTFCharacter(byte c) {
		return (c >= '0' && c <= '9') || (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || c == ' ' 
				|| c == '-' || c == '\'' || c == '\n' || c == '/' || c == ',' || c == '.' || c == '?';
	}
}
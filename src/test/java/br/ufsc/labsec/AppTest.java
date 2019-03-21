package br.ufsc.labsec;

import static org.junit.Assert.fail;

import java.util.Map;

import org.junit.Assert;
import org.junit.Test;


public class AppTest {
	
	@Test
	public void shouldConvertHexStringToBase64Correctly() {
		try {
			String convertToBase64 = HexToBase64Converter.convertToBase64("49276d206b696c6c6"
				+ "96e6720796f757220627261696e206c696b65206120706f69736f6e6f7573206d7573"
				+ "68726f6f6d");
			Assert.assertEquals("SSdtIGtpbGxpbmcgeW91ciBicmFpbiBsaWtlIGEgcG9pc29ub3VzIG11c2hyb29t", convertToBase64);
		} catch (Exception e) {
			fail("Um erro inesperado ocorreu");
		}
	}
	
	@Test
	public void shouldReturnEncryptedStringWithXOR() {
		try {
			String xorCombination = XORCombinationCryptography.xorCombination("1c0111001f010100061a024b53535009181c",
					"686974207468652062756c6c277320657965");
			Assert.assertEquals("746865206b696420646f6e277420706c6179", xorCombination);
		} catch (Exception e) {
			fail("Um erro inesperado ocorreu");
		}
	}
	
	@Test
	public void shouldDescryptMessage() {
		try {
			Map<Character, Map<String, Double>> decryptMessage = SingleXORCipher.decryptMessage("1b37373331363f78151b7f2b783431333d78397828372d363c78373e783a393b3736");
			char key = (Character) decryptMessage.keySet().toArray()[0];
			Assert.assertEquals('X', key);
			String message = (String) decryptMessage.get(key).keySet().toArray()[0];
			Assert.assertEquals("Cooking MC's like a pound of bacon", message);
		} catch (Exception e) {
			fail("Um erro inesperado ocorreu");
		}
	}
}
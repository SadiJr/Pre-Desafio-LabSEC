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
	
	@Test
	public void shouldDescryptFile() {
		try {
			Map<Character, String> decryptFile = SingleCharacterXOR.decryptFile();
			char key = (Character) decryptFile.keySet().toArray()[0];
			Assert.assertEquals('5', key);
			String message = (String) decryptFile.get(key);
			Assert.assertEquals("Now that the party is jumping", message);
		} catch (Exception e) {
			fail("Um erro inesperado ocorreu");
		}
	}
	
	@Test
	public void shouldEncryptTextWithRepeatingXOR() {
		try {
			String encrypt = RepeatingXOREncryptor.encrypt("Burning 'em, if you ain't quick and nimble\n" + 
					"I go crazy when I hear a cymbal");
			Assert.assertEquals("0b3637272a2b2e63622c2e69692a23693a2a3c6324202d623d63343c2a26226324272765272"
					+ "a282b2f20430a652e2c652a3124333a653e2b2027630c692b20283165286326302e27282f", encrypt);
		} catch (Exception e) {
			fail("Um erro inesperado ocorreu");
		}
	}
}
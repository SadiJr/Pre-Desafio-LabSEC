package br.ufsc.labsec;

import static org.junit.Assert.fail;

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
}
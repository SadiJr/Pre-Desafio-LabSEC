package br.ufsc.labsec;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

public class HexToBase64Converter {
	
	public static String convertToBase64(String hex) throws DecoderException {
		return new String(Base64.encodeBase64(Hex.decodeHex(hex.toCharArray())));
	}
}
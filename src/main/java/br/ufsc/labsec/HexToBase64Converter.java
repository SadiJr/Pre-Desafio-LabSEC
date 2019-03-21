package br.ufsc.labsec;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

/**
 * 
 * @author Sadi
 * 
 * Não foi preciso pensar muito. Já tinha trabalhado antes com conversões de vários 
 * formatos para Base64, e já tinha conhecimento da biblioteca Apache Commons.
 *
 */
public class HexToBase64Converter {
	
	public static String convertToBase64(String hex) throws DecoderException {
		return new String(Base64.encodeBase64(Hex.decodeHex(hex.toCharArray())));
	}
	
	public static String convertToHex(String base64) {
		return new String(Hex.encodeHex(Base64.decodeBase64(base64.getBytes())));
	}
}
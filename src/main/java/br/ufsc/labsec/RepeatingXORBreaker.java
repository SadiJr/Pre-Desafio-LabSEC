package br.ufsc.labsec;

import java.io.File;
import java.io.IOException;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.io.FileUtils;

public class RepeatingXORBreaker {
	
	public static String breakXORCryptography() throws IOException {
		byte[] readFileToByteArray = FileUtils.readFileToByteArray(new File("src/main/resources/challenge_06.txt"));
		String hex = HexToBase64Converter.convertToHex(new String(readFileToByteArray));
		for(int i = 2; i < hex.length(); i += 2) {
			
		}
		return "";
	}
	//Inicialmente em Base64
	//Usa XOR com repetição
	//A chave tem o tamanho multiplo de 2 (?), varia de 2 a 40 //TODO testar primeiramente todo o intervalo e depois os
															   // multiplos
	//Solução usa a Distância de Hamming
	//As chaves cuja Hamming distance são menores provavelmente são a chave (Brute Force ?)
	//Usa um unico caracter na chave do XOR
	//FIXME Desafio 6 pulado!!!!!!
	public static int hammingDistance(String firstWord, String secondWord) {
		int max = Math.max(firstWord.length(), secondWord.length());
		int result = 0;
		
		for(int i = 0; i < max; i++) {
			if(firstWord.charAt(i) != secondWord.charAt(i)) {
				result++;
			}
		}
		System.out.println(result);
		return result;
	}
}

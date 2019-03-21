package br.ufsc.labsec;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.io.FileUtils;
/**
 * 
 * @author Sadi
 * 
 * Devido a um erro de interpretação meu (que culpo minha leitura apenas "por cima"), 
 * tentei descriptografar o arquivo inteiro com a chave encontrada ao invés de uma única linha.
 * 
 * Após perceber meu erro, foi fácil concluir o desafio. 
 * 
 * Fora isso, alterei a classe do desafio 3 para abranger também o desafio 4.
 *
 */

public class SingleCharacterXOR {

	public static Map<Character, String> decryptFile() throws IOException, DecoderException {
		byte[] readFileToByteArray = FileUtils.readFileToByteArray(new File("src/main/resources/challenge_04.txt"));
		String[] split = new String(readFileToByteArray).split("\n");
		
		double max = 0.0;
		char key = ' ';
		String line= "";
		
		//TODO Otimizar método (3 fors aninhados é péssimo para o desempenho)
		//TODO Remover comentários.
		for (String string : split) {
			Map<Character, Map<String, Double>> decryptMessage = SingleXORCipher.decryptMessage(string);
			
			for (char k : decryptMessage.keySet()) {
				Map<String, Double> lines = decryptMessage.get(k);
				
				for (String message : lines.keySet()) {
					double score = lines.get(message);
					
					if(score > max) {
						max = score;
						key = k;
						line = message;
					}
				}
			}
		}
//		System.out.println("Key = " + key + "\nLine = " + line);
		Map<Character, String> result = new HashMap<Character, String>();
		result.put(key, line.trim());
		return result;
	}
}
package br.ufsc.labsec;

import java.util.Arrays;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

/**
 * 
 * @author Sadi
 * 
 * Desafio um pouco mais complicado...
 * Foi necessário percorrer todas as linhas, separando-as em byte[] de 16 (que são os blocos criptografados) e
 * verificando se dois blocos eram iguais.
 * 
 * Como o ECB criptografa blocos de texto puro iguais em blocos de texto criptografados iguais, ele não oculta os padrões de dados.
 * 
 * Por isso, bastou agrupar os dados em blocos de 16 bytes e verificar os trechos repetidos. A princípio pensei em fazer um contador para
 * verificar qual linha tinha mais repetições, mas, conforme eu ia desevolvendo (por tentativa e erro, admito), me dei conta de que 
 * apenas uma linha estava cifrada (erro de interpretação meu novamente) e que, portanto, não era necessário tal nível de complexidade
 *
 */
public class DetectAES {

	public static String detect(String text) throws DecoderException {
		String result = "";
		String[] lines = text.split("\n");
//		int line = 1;
		
		for (String l : lines) {
			byte[] decodeHex = Hex.decodeHex(l.toCharArray());
			byte[][] data = new byte[decodeHex.length / 16][16];
			int pos = 0;
			
			for (int i = 0; i < decodeHex.length / 16; i++) {
				for (int j = 0; j < 16; j++)
					data[i][j] = decodeHex[pos++];
			}
			
			boolean find = false;
			for (int i = 0; i < data.length; i++) {
				
				//TODO Otimizar método depois (3 FORs aninhados de novo).
				//Ver outros "TODO" espalhados pelo projeto.
				if (find)
					break;
				
				for (int j = 0; j < data.length; j++) {
					if (i == j)
						continue;
					if (Arrays.equals(data[i], data[j])) {
//						System.out.println("i = " + new String(Hex.encodeHex(new String(data[i]).getBytes())));
//						System.out.println("j = " + new String(Hex.encodeHex(new String(data[j]).getBytes())));
//						System.out.println("A linha " + line + " deve ser ECB");
//						System.out.println(l);
						result = l;
						find = true;
						break;
					}
				}
			}
//			line++;
		}
		return result;
	}
}
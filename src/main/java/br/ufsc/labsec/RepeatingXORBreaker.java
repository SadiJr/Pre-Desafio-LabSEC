package br.ufsc.labsec;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

/**
 * 
 * @author Sadi
 * 
U2FsdGVkX1/ozYfIxzbKm036euLSgrgXI/5BtN+kEH7mjVQ3cWpEl7w2vh5em4ki
E9pn2VUVzbzWEOUI1E6ICNqkkbPsGT6AavkeeLuu1mA=

 * Quebre a criptografia acima, a chave é ************ (sendo que cada * é uma letra, a chave é insteiramente composta por letras).
 * e a criptografia usada foi a aes-128-ecb.
 */
public class RepeatingXORBreaker {
	
	
//		byte[] hex = HexToBase64Converter.convertToHex(text).getBytes();
//		Map<Integer, Integer> allDistances = new HashMap<Integer, Integer>();
//		
//		for(int i = 2; i < 40; i++) {
//			byte[] data1 = Arrays.copyOfRange(hex, 0 * i, 1 * i);
//			byte[] data2 = Arrays.copyOfRange(hex, 1 * i, 2 * i);
//			byte[] data3 = Arrays.copyOfRange(hex, 2 * i, 3 * i);
//			byte[] data4 = Arrays.copyOfRange(hex, 3 * i, 4 * i);
//			
//			int total = hammingDistance(data1, data2);
//			total += hammingDistance(data1, data3);
//			total += hammingDistance(data1, data4);
//			total += hammingDistance(data2, data3);
//			total += hammingDistance(data2, data4);
//			total += hammingDistance(data3, data4);
//			
//			allDistances.put(i, (total / i));
//		}
//		
//		int minDistance = 100000000;
//		int key = 0;
//		for (int k : allDistances.keySet()) {
//			System.out.println("Key = " + k + "\nDistance = " + allDistances.get(k) + "\n");
//			int distance = allDistances.get(k);
//			if(distance < minDistance) {
//				minDistance = distance;
//				key = k;
//			}
//		}
//		
//		for (int i = 0; i < 1; i++) {
//			System.out.println("trying keysize " + minDistance);
//			byte[] k = new byte[key];
//
//			byte[][] data = new byte[hex.length / key][key];
//			int pos = 0;
//			for (int j = 0; j < hex.length / key; j++)
//			{
//				for (int l = 0; l < key; l++)
//					data[j][l] = hex[pos++];
//			}
//
//			byte[][] transpose = new byte[key][hex.length / key];
//
//			// transpose the data to let us access elements with the same xor
//			// key
//			for (int r = 0; r < data.length; r++)
//			{
//				for (int c = 0; c < data[0].length; c++)
//				{
//					transpose[c][r] = data[r][c];
//				}
//			}
//
//			int maxFrequently = 0;
//			byte a = 1;
//			for (int ke = 0; ke < key; ke++) {
//				// here we should have a buffer with every byte using the same
//				// single byte xor key
//				// now solve the single byte xor
//				// System.out.print("possible values for key pos " + k + ": ");
//				for (int j = 0; j < 255; j++) {
//					byte[] decoded = XORCombinationCryptography.xorCombination(transpose[ke], (byte) j);
//					int frequency = SingleXORCipher.frequency(decoded);
//					if(frequency > maxFrequently) {
//						maxFrequently = frequency;
//						System.out.println((byte)j);
//					}
//					byte[] xorCombination = XORCombinationCryptography.xorCombination(transpose[ke], (byte) 4);
//					System.out.println(HexToBase64Converter.convertToHex(new String(xorCombination)));
//				}
//			}
//		}

	public static int hammingDistance(byte[] first, byte[] second) {
		int max = Math.max(first.length, second.length);
		int min = Math.min(first.length, second.length);
		int result = 0;
		
		for(int i = 0; i < min; i++) {
			int distance = first[i] ^ second[i];
			result += Integer.bitCount(distance);
		}
		return (result + 8 * (max - min) );
	}
	
	public static double normalizedHammingDistance(byte[] first, byte[] second) {
		int hammingDistance = hammingDistance(first, second);
		int max = Math.max(first.length, second.length);

		return (double) hammingDistance / (double) max;
	}
	
	public static String breakXORCryptography(String text) throws IOException, DecoderException {
		byte[] hex = HexToBase64Converter.convertToHex(text).getBytes();
		double minDistance = 1000000.000000;
		int key = 0;
		
		for (int i = 2; i <= 40; i++) {
			int blocks = hex.length / i - 1;
			double distance = 0.0;
			
			for (int j = 0; j < blocks; j++) {
				int from = i * j;
				int to = Math.min(i * (j + 1), hex.length);
				
				byte[] data1 = Arrays.copyOfRange(hex, from, to);
				byte[] data2 = Arrays.copyOfRange(hex, from, to+1);
				
				distance += normalizedHammingDistance(data1, data2);
			}
			
			double resultingDistance = distance / blocks;
			
			if(minDistance > resultingDistance) {
				minDistance = resultingDistance;
				key = i;
			}
			
			Map<Character, Map<String, Double>> decryptMessage = SingleXORCipher.decryptMessage(new String(hex));
			System.out.println(decryptMessage.keySet().toArray()[0]);
			byte[] xorCombination = XORCombinationCryptography.xorCombination(hex, (byte) key);
			System.out.println(new String(xorCombination));
		}
		System.out.println("Key: " + key);
		byte[] xorCombination = XORCombinationCryptography.xorCombination(hex, (byte) 'b');
		System.out.println(new String(Hex.decodeHex(Hex.encodeHex(new String(xorCombination).getBytes()))));
		
		byte[] aux = aux(hex, key);
		int i = aux.length / key + (aux.length % key == 0 ? 0 : 1);

		
		
		return "";
	}
	
	public static byte[] aux(byte[] hex, int key) {
		int blockSize = hex.length / key + (hex.length % key == 0 ? 0 : 1);
		int range = hex.length % key == 0? 0 : key - hex.length;
		
		byte[] result = new byte[hex.length + range];
		
		for (int i = 0; i < hex.length; i++) {
			byte b = hex[i];
			int line = i / key;
			int collumn = i % key;
			
			int newIndex = collumn * blockSize + line;
			result[newIndex] = b;
		}
		return result;
	}
}
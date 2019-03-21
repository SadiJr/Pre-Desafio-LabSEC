package br.ufsc.labsec;

import java.util.Map;

public class App {
	public static void main(String[] args) {
		try {
			Map<Character, String> decryptFile = SingleCharacterXOR.decryptFile();
			System.out.println(decryptFile.keySet().toArray()[0]); 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

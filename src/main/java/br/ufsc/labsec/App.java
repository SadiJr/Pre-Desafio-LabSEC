package br.ufsc.labsec;

public class App {
	public static void main(String[] args) {
		try {
			
			String xorCombination = XORCombinationCryptography.xorCombination("1c0111001f010100061a024b53535009181c", 
				"686974207468652062756c6c277320657965");
			System.out.println(xorCombination);
		} catch (Exception e) {
		}
	}
}

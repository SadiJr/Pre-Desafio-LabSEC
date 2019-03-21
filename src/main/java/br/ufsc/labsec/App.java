package br.ufsc.labsec;

public class App {
	public static void main(String[] args) {
		try {
			
			SingleXORCipher.decryptMessage("1b37373331363f78151b7f2b783431333d78397828372d363c78373e783a393b3736");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

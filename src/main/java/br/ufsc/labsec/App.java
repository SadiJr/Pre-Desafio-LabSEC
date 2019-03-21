package br.ufsc.labsec;

public class App {
	public static void main(String[] args) {
		try {
			SingleCharacterXOR.decryptFile();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

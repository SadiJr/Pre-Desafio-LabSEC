package br.ufsc.labsec;

public class App {
	public static void main(String[] args) {
		try {
			RepeatingXOREncryptor.encrypt("Burning 'em, if you ain't quick and nimble\n");
			RepeatingXOREncryptor.encrypt("I go crazy when I hear a cymbal");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

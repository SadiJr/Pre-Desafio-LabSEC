package br.ufsc.labsec;

public class App {
	public static void main(String[] args) {
		try {
			System.out.println("Iniciando testes!");
			String convertToBase64 = HexToBase64Converter.convertToBase64("49276d206b696c6c6"
					+ "96e6720796f757220627261696e206c696b65206120706f69736f6e6f7573206d7573"
					+ "68726f6f6d");
			System.out.println("Resultado da conversão = " + convertToBase64);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}

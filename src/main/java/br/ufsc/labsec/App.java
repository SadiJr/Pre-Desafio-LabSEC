package br.ufsc.labsec;

import java.io.File;

import org.apache.commons.io.FileUtils;

public class App {
	public static void main(String[] args) {
		try {
			String file = new String(FileUtils.readFileToByteArray(new File("src/main/resources/challenge_08.txt")));
			DetectAES.detect(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

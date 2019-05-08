package compiler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import analyzer.Lexer;
import analyzer.Token;

public class Compiler {

	private static final String SAMPLE_PATH = "sample/first.mir";
	
	public static void main(String[] args) {
		File sample = new File(SAMPLE_PATH);
		String pragram = Compiler.readFile(sample);
		long start = System.currentTimeMillis();
		List<Token> tokens = new Lexer(pragram).tokenize();
		System.out.println('\t' + "Lexer time: " + (System.currentTimeMillis() - start) + "ms" + '\n');
		System.out.println('\t' + pragram + '\n');
		tokens.stream().forEach(Compiler::printToken);
	}
	private static void printToken(Token token) {
		System.out.println(token.toString());
	}
	
	private static String readFile(File sample) {
		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();
		String st; 
		try {
			br = new BufferedReader(new FileReader(sample));
			while ((st = br.readLine()) != null) {
				sb.append(st);
			}
		} catch (IOException | NullPointerException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

}

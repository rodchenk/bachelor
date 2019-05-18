package mir;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import mir.analyzer.Lexer;
import mir.analyzer.Parser;
import mir.analyzer.Token;
import mir.analyzer.ast.Expression;
import mir.analyzer.ast.Statement;
import mir.utility.TimeMeasurement;

public class Compiler {

	private static final String SAMPLE_PATH = "sample/first.mir";
	
	public static void main(String[] args) {
		String pragram = Compiler.readFile(new File(SAMPLE_PATH));
		
		TimeMeasurement.setMeasurement("Lexer");
		List<Token> tokens = new Lexer(pragram).tokenize();
		System.out.println('\t' + "Lexer time: " + TimeMeasurement.getResult("Lexer") + "ms" + '\n');
		tokens.stream().forEach(Compiler::printToken);
		
		System.out.println("--------------------");
		
		TimeMeasurement.setMeasurement("Parser");
		List<Statement> statements = new Parser(tokens).parse();
		System.out.println('\t' + "Parser time: " + TimeMeasurement.getResult("Parser") + "ms" + '\n');
		
		statements.stream().forEach(e->{
			System.out.println(e); 
			e.execute();
			}
		);
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
				sb.append(st+'\n');
			}
		} catch (IOException | NullPointerException e) {
			e.printStackTrace();
		}
		System.out.println(sb.toString() + '\n');
		return sb.toString();
	}

}

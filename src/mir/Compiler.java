package mir;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import mir.analyzer.Lexer;
import mir.analyzer.Parser;
import mir.analyzer.Token;
import mir.analyzer.ast.Statement;
import mir.utility.TimeMeasurement;

public class Compiler {

	private static final String SAMPLE_PATH = "sample/first.mir";
	
	public static void main(String[] args) throws IOException {
		String pragram = new String(Files.readAllBytes(Paths.get(SAMPLE_PATH)), "UTF-8");
		
		TimeMeasurement.setMeasurement("Lexer");
		List<Token> tokens = new Lexer(pragram).tokenize();
		System.out.println('\t' + "Lexer time: " + TimeMeasurement.getResult("Lexer") + "ms" + '\n');
		tokens.stream().forEach(Compiler::printToken);
		
		System.out.println("----------------------------------------");
		
		TimeMeasurement.setMeasurement("Parser");
		List<Statement> statements = new Parser(tokens).parse();
		System.out.println('\t' + "Parser time: " + TimeMeasurement.getResult("Parser") + "ms" + '\n');
		
		statements.stream().forEach(Statement::execute);
	}
	
	private static void printToken(Token token) {
		System.out.println(token.toString());
	}
	
}

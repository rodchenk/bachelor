package mir;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import mir.analyzer.Lexer;
import mir.analyzer.Parser;
import mir.analyzer.Token;
import mir.analyzer.ast.Statement;
import mir.utility.TimeMeasurement;

public class Interpreter {

	private static final String SAMPLE_PATH = "sample/second.mir";
	
	private static void run(String program) {
		TimeMeasurement.setMeasurement("Lexer");
		List<Token> tokens = new Lexer(program).tokenize();
		System.out.println('\t' + "Lexer time: " + TimeMeasurement.getResult("Lexer") + "ms" + '\n');
		tokens.stream().forEach(System.out::println);
		
		System.out.println("----------------------------------------");
		
		TimeMeasurement.setMeasurement("Parser");
		Statement source = new Parser(tokens).parse();
		System.out.println('\t' + "Parser time: " + TimeMeasurement.getResult("Parser") + "ms" + '\n');

		source.execute();
	}
	
	public static void main(String[] args) throws IOException {
		String program = new String(Files.readAllBytes(Paths.get(SAMPLE_PATH)), "UTF-8");
		Interpreter.run(program);
	}
}

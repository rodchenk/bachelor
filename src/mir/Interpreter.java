package mir;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import mir.analyzer.IncludeScanner;
import mir.analyzer.Lexer;
import mir.analyzer.Parser;
import mir.analyzer.Token;
import mir.analyzer.ast.Statement;
import mir.utility.TimeMeasurement;

public class Interpreter {

	private static final String SAMPLE_PATH = "sample/";
	private static final String FILE_NAME = "third.mir";
	
	private static void run(String program) {
		TimeMeasurement.setMeasurement("Lexer");
		IncludeScanner scanner = new IncludeScanner(SAMPLE_PATH, program);
		String program_with_includes = scanner.scan();
		
		//System.out.println(program_with_includes);
		
		List<Token> tokens = new Lexer(program_with_includes).tokenize();
		System.out.println('\t' + "Lexer time: " + TimeMeasurement.getResult("Lexer") + "ms" + '\n');
		tokens.stream().forEach(System.out::println);
		
		System.out.println("----------------------------------------");
		
		TimeMeasurement.setMeasurement("Parser");
		Statement source = new Parser(tokens).parse();
		System.out.println('\t' + "Parser time: " + TimeMeasurement.getResult("Parser") + "ms" + '\n');

		source.execute();
	}
	
	public static void main(String[] args) throws IOException {
		String program = new String(Files.readAllBytes(Paths.get(SAMPLE_PATH + FILE_NAME)), "UTF-8");
		Interpreter.run(program);
	}
}

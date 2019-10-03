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
	
	private static void run(String path, boolean verbose) {
		
		String program = null;
		try {
			program = new String(Files.readAllBytes(Paths.get(path)), "UTF-8");
		} catch (IOException e) {
			System.err.println("File " + path + " could not be found");
			System.exit(0);
		}
		TimeMeasurement.setMeasurement("Lexer");
		IncludeScanner scanner = new IncludeScanner(SAMPLE_PATH, program);
		String program_with_includes = scanner.scan();
		
		//System.out.println(program_with_includes);
		
		List<Token> tokens = new Lexer(program_with_includes).tokenize();
		
		if(verbose) {
			System.out.println('\t' + "Lexer time: " + TimeMeasurement.getResult("Lexer") + "ms" + '\n');
			System.out.println("----------------------------------------");
			tokens.stream().forEach(System.out::println);
			
			System.out.println("----------------------------------------");
		}
		
		TimeMeasurement.setMeasurement("Parser");
		Statement source = new Parser(tokens).parse();
		
		if(verbose) {
			System.out.println('\t' + "Parser time: " + TimeMeasurement.getResult("Parser") + "ms" + '\n');
		}
		
		source.execute();
	}
	
	public static void main(String[] args) throws IOException {
		switch (args.length) {
			case 1:	Interpreter.run(args[0], false); break;
				
			case 2:	Interpreter.run(args[0], true);  break;
			
			case 0:
			default:
				System.out.println("mir language. Version 0.1. 2019. Author: Mischa Rodchenkov");
				System.out.println("USAGE:");
				System.out.println("\t mir [path_to_file]");
				System.out.println("\t mir [path_to_file] -v [verbose]");
				System.exit(0);
				break;
		}

	}
}

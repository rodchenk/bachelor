package compiler;

import java.util.List;

import analyzer.Lexer;
import analyzer.Token;

public class Compiler {

	private static final String PROGRAM_TEXT = "123"; 
	
	private static void printToken(Token token) {
		System.out.println(token.toString());
	}
	
	public static void main(String[] args) {

		List<Token> tokens = new Lexer(PROGRAM_TEXT).tokenize();
		
		tokens.stream().forEach(Compiler::printToken);
	}

}

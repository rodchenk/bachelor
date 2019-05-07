package analyzer;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author M.Rodchenkov
 * @category Lexer Klasse. Prueft alle Zeichen im Program Context und weist die TokenTypes zu;
 * Nach der Instanzierung muss die Methode tokenize() aufgerufen.
 */
public class Lexer {

	String context;
	int position, context_length;
	List<Token> tok_list;
	
	public Lexer(String context) {
		this.context = context;
		this.tok_list = new ArrayList<>();
		this.position = 0;
		this.context_length = context.length();
	}
	
	public List<Token> tokenize() {
		
		List<String> lexemes = this.getLexemes();
		
		for(String lexeme: lexemes) {
			tok_list.add(new Token(LexerUtility.getLexemeType(lexeme), lexeme));
		}
		return tok_list;
	}
	
	private List<String> getLexemes() {
		StringBuilder sb;
		List<String> lexemes = new ArrayList<>();
		//TODO whilte loop might be false
		while(hasNext()) {
			sb = new StringBuilder();
			char current_character;
			do {
				current_character = next();
				if(!Character.isWhitespace(current_character))
					sb.append(current_character);
			}while(hasNext() && LexerUtility.isSameType(String.valueOf(context.charAt(position)), String.valueOf(current_character)) );
			
			lexemes.add(sb.toString());
		}
		return lexemes;
	}

	
	/**
	 * @return boolean if there is next character in program context
	 */
	private boolean hasNext() {
		return position < context_length;
	}
	
	/**
	 * @return next character of context and increment it by one
	 */
	private char next() {
		return context.charAt(position++);
	}
}

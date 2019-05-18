package mir.analyzer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static mir.utility.LexerUtility.*;

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
	boolean quotes_opened = false;
	
	public Lexer(String context) {
		this.context = remove_comments_and_spaces(context);
		this.tok_list = new ArrayList<>();
		context_length = this.context.length();
	}

	public List<Token> tokenize() {
		List<String> lexemes = this.getLexemes();
		
		for(String lexeme: lexemes) {
			tok_list.add(new Token(getLexemeType(lexeme), lexeme));
		}
		
		tok_list.stream() // TODO -> hier werden die Klammer bei String geloescht. Muss etwas effektives finden
			.filter(e->e.getType().equals(TokenType.TEXT))
			.forEach(e->e.setValue(e.getValue().substring(1, e.getValue().length() - 1)));
		
		return tok_list;
	}
	
	private List<String> getLexemes() {
		StringBuilder sb;
		String current_char_to_string;
		boolean should_take_next = false;
		List<String> lexemes = new ArrayList<>();

		while(hasNext()) {
			sb = new StringBuilder();
			do {
				current_char_to_string = String.valueOf(next());
				
				while(quotes_opened) {
					sb.append(current_char_to_string);
					current_char_to_string = String.valueOf(next());
				}
				
				sb.append(current_char_to_string);
				should_take_next = hasNext() && 
						!isBracket(current_char_to_string) && 
						!isOperator(current_char_to_string) &&
						isSameType(String.valueOf(context.charAt(position)), current_char_to_string);
			}while(should_take_next);

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
		char next = context.charAt(position++);
		if(next == '"') 
			quotes_opened = !quotes_opened;
		return next;
	}
}

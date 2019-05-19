package mir.analyzer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import mir.utility.LexerUtility;

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
	
	final private String PRINT = "print", IF = "if", ELSE = "else", FOR = "for";
	private final char LPT = '(', RPT = ')', LCB = '{', RCB = '}', LSB = '[', RSB = ']', 
			EOL = ';', ALLOC = '=', PLUS = '+', MINUS = '-', 	STAR = '*', SLASH = '/',  GT = '>', LT = '<';
	
	private final List<Character> BRACKETS = Arrays.asList(LPT, RPT, LSB, RCB, LSB, RSB);
	private final List<Character> OPERATORS = Arrays.asList(PLUS, MINUS, STAR, SLASH, ALLOC, GT, LT);
	
	private final List<String> KEY_WORDS = Arrays.asList(PRINT, IF, ELSE, FOR);

	
	public Lexer(String context) {
		this.context = remove_comments_and_spaces(context);
		this.tok_list = new ArrayList<>();
		context_length = this.context.length();
	}

	public List<Token> tokenize() {
		return getLexemes();
	}
	
	private List<Token> getLexemes() {
		char next;
		List<Token> lexemes = new ArrayList<>();

		while(hasNext()) {
			next = next();
						
			if(Character.isDigit(next)) {
				lexemes.add(tokenizeNumber(next));
				continue;
			}
			if(Character.isLetter(next) || quotes_opened) {
				lexemes.add(tokenizeID(next));
				continue;
			}
			if(OPERATORS.contains(next)) {
				lexemes.add(tokenizeOperator(next));
				continue;
			}
			if(BRACKETS.contains(next)) {
				lexemes.add(tokenizeBracket(next));
				continue;
			}
			if(next == EOL) {
				lexemes.add(new Token(TokenType.EOL));
				continue;
			}
			throw new RuntimeException("Unknown lexeme: " + next);
		}
		return lexemes;
	}
	
	private Token tokenizeBracket(char next) {
		switch(next) {
			case LPT: return new Token(TokenType.LPT);
			case RPT: return new Token(TokenType.RPT);
			case LCB: return new Token(TokenType.LCB);
			case RCB: return new Token(TokenType.RCB);
			case LSB: return new Token(TokenType.LSB);
			case RSB: return new Token(TokenType.RSB);
		}
		throw new RuntimeException("Unknown bracket: " + next);
	}

	private Token tokenizeOperator(char next) {
		StringBuilder sb = new StringBuilder();
		char current = next;
		if(current == ALLOC) 
			return new Token(TokenType.ALLOC);
		
//		while(hasNext() && isOperator(current)) { // parse multi operators like >=, ++, +=, >= and so on
//			sb.append(current);
//			current = next();
//		}
//		prev(); // go one char back
		
		switch(next) {
			case(PLUS): return new Token(TokenType.PLUS);
			case(MINUS):return new Token(TokenType.MINUS);
			case(STAR): return new Token(TokenType.STAR);
			case(SLASH):return new Token(TokenType.SLASH);
			case(GT): 	return new Token(TokenType.GT);
			case(LT): 	return new Token(TokenType.LT);
		}
		throw new RuntimeException("Unknown operator: (" + sb.toString() + ")");
	}
	
	private Token tokenizeNumber(char next) {
		StringBuilder sb = new StringBuilder();
		char current = next;
		while(hasNext() && (Character.isDigit(current) || current == '.')) {
			sb.append(current);
			current = next();
		}
		prev();
		return new Token(TokenType.NUMBER, sb.toString());
	}
	
	private Token tokenizeID(char next) {
		//TODO string like \"
		StringBuilder sb = new StringBuilder();
		char current = next;
		if(quotes_opened) { // parse String, e.g "Hello"
			return tokenizeText();
		}
		
		while(hasNext() && (Character.isAlphabetic(current) )) { // build words
			sb.append(current);
			current = next();
		}

		prev();
		String token_value = sb.toString();
		
		if(KEY_WORDS.contains(token_value)) {
			switch(token_value) {
				case PRINT: return new Token(TokenType.PRINT);
				case IF: return new Token(TokenType.IF);
				case ELSE: return new Token(TokenType.ELSE);
				case FOR: return new Token(TokenType.FOR);
			}
		}
		return new Token(TokenType.ID, token_value);
	}

	
	private Token tokenizeText() {
		StringBuilder sb = new StringBuilder();
		char current = next(); // skip opened and closed "
		while(quotes_opened) {
			if(current == '\\') {
				current = next();
				switch(current) {
					case 'n': {
						current = next();
						sb.append('\n');
						continue;
					}
					case 't': {
						current = next();
						sb.append('\t');
						continue;
					}
				}
			}
			sb.append(current);
			current = next();
		}
		return new Token(TokenType.TEXT, sb.toString());
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
	
	private void prev() {
		position-=1;
	}
}

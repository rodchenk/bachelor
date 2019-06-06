package mir.analyzer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 
 * @author M.Rodchenkov
 * @category Lexer Klasse. Prueft alle Zeichen im Program Context und weist die TokenTypes zu;
 * Nach der Instanzierung muss die Methode tokenize() aufgerufen.
 */
public class Lexer {

	private final String context;
	private final int context_length;
	private int position;
	private boolean quotes_opened = false;
	
	private final String TRUE = "true", FALSE = "false", PRINT = "print", IF = "if", ELSE = "else", FOR = "for", WHILE = "while", EQ = "==", GTEQ = ">=", LTEQ = "<=", NOTEQ = "!=";
	private final char LPT = '(', RPT = ')', LCB = '{', RCB = '}', LSB = '[', RSB = ']', NEG = '!',
			EOL = ';', ALLOC = '=', PLUS = '+', MINUS = '-', 	STAR = '*', SLASH = '/',  GT = '>', LT = '<';
	
	private final List<Character> BRACKETS = Arrays.asList(LPT, RPT, LCB, RCB, LSB, RSB);
	private final List<Character> OPERATORS = Arrays.asList(PLUS, MINUS, STAR, SLASH, ALLOC, GT, LT, NEG);
	
	private final List<String> DUAL_OPERATORS = Arrays.asList(EQ, LTEQ, GTEQ, NOTEQ);
	private final List<String> KEY_WORDS = Arrays.asList(PRINT, IF, ELSE, FOR, WHILE, TRUE, FALSE);

	
	public Lexer(String context) {
		this.context = remove_comments_and_spaces(context);
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
			if(Character.isLetter(next)) {
				lexemes.add(tokenizeID(next));
				continue;
			}
			if(quotes_opened) {
				lexemes.add(tokenizeText());
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
			if(next == ' ' || next == '\n' || next == '\t') continue; // skip whitespaces
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
		char current = next;
		char following = next(); 
		
		// ==, <=, >=
		if(OPERATORS.contains(following)) {
			String dual = (String)(current + "" + following);
			if(DUAL_OPERATORS.contains(dual)) {
				switch(dual) {
					case EQ: 	return new Token(TokenType.EQ);
					case LTEQ: 	return new Token(TokenType.LTEQ);
					case GTEQ: 	return new Token(TokenType.GTEQ);
					case NOTEQ: return new Token(TokenType.NOTEQ);
				}
			}
		}
		prev();

		switch(next) {
			case ALLOC: return new Token(TokenType.ALLOC);
			case PLUS: 	return new Token(TokenType.PLUS);
			case MINUS:	return new Token(TokenType.MINUS);
			case STAR: 	return new Token(TokenType.STAR);
			case SLASH:	return new Token(TokenType.SLASH);
			case GT: 	return new Token(TokenType.GT);
			case LT: 	return new Token(TokenType.LT);
		}
		
		throw new RuntimeException("Unknown operator: (" + next + ")");
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
		final StringBuilder sb = new StringBuilder();
		char current = next;
		
		while(hasNext() && (Character.isAlphabetic(current) )) {
			sb.append(current);
			current = next();
		}

		prev();
		String token_value = sb.toString();
		
		if(KEY_WORDS.contains(token_value))
			return tokenizeKeyword(token_value);

		return new Token(TokenType.ID, token_value);
	}

	
	private Token tokenizeKeyword(String token_value) {
		switch(token_value) {
			case PRINT: return new Token(TokenType.PRINT);
			case IF: 	return new Token(TokenType.IF);
			case WHILE: return new Token(TokenType.WHILE);
			case ELSE: 	return new Token(TokenType.ELSE);
			case FOR: 	return new Token(TokenType.FOR);
			case TRUE:	return new Token(TokenType.TRUE);
			case FALSE: return new Token(TokenType.FALSE);
		}
		throw new RuntimeException("Unknown keyword " + token_value);
	}

	private Token tokenizeText() {
		final StringBuilder sb = new StringBuilder();
		char current = next(); // skip open quote
		while(true) {
			if(current == 92) {
				current = next();
				switch(current) {
					case 34:  current = next(); sb.append('\"'); continue; // quote
					case 110: current = next(); sb.append('\n'); continue; // new line
					case 116: current = next();	sb.append('\t'); continue; // tabulator
				}
			}
			
			if(current == 34) { // closed quote
				quotes_opened = false;
				break;
			}
			
			sb.append(current);
			current = next();
		}
		return new Token(TokenType.TEXT, sb.toString());
	}
	
	/**
	 * 
	 * @param String Program context
	 * @return String context without comments, whitespaces and new lines
	 */
	private static String remove_comments_and_spaces(String context) {
		return context.replaceAll("#[^\\n\\r]+", ""). //one line comments with #
				replaceAll("\\/\\*[\\s\\S]*?\\*\\/", "").//multiline comments with /* COMMENT */
				replaceAll("\\n|\\t", "").
				//replaceAll("/((\\r\\n|\\n|\\r)$)|(^(\\r\\n|\\n|\\r))|^\\s*$/gm", " "); // 
				replaceAll("\\s+(?=([^\"]*\"[^\"]*\")*[^\"]*$)", " ");//all whitespaces that are not in quotes
	}

	/**
	 * @return boolean if there is next character in program context
	 */
	private boolean hasNext() {
		return position < context_length;
	}
	
	/**
	 * @return next character of context and increase it by one
	 */
	private char next() {
		char next = context.charAt(position++);
		if(next == '"') 
			quotes_opened = true;
		return next;
	}
	
	/**
	 * @return void
	 * @param decrease current context position by 1
	 */
	private void prev() {
		position-=1;
	}
}

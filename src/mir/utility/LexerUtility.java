package mir.utility;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import mir.analyzer.TokenType;

public class LexerUtility {

	static final String LPT = "(", RPT = ")", 
						LCB = "{", RCB = "}", 
						LSB = "[", RSB = "]",
						PLUS = "+", MINUS = "-", 
						STAR = "*", SLASH = "/",
						PRINT = "print";
	
	final static List<String> OPERATORS = Arrays.asList(PLUS, MINUS, STAR, SLASH);
	final static List<String> BRACKETS = Arrays.asList(LPT, RPT, LSB, RCB, LSB, RSB);
	
	final static List<String> KEY_WORDS = Arrays.asList(PRINT);

	public static TokenType getLexemeType(String lex) {
		String lexeme = lex.trim();
		
		if(isID(lexeme)) 		return TokenType.ID;
		if(isDigit(lexeme)) 	return TokenType.NUMBER;
		if(isEOL(lexeme)) 		return TokenType.EOL;
		if(isString(lexeme))	return TokenType.TEXT;
		if(isAlloc(lexeme))		return TokenType.ALLOC;
		if(isKeyword(lexeme)) {
			switch(lexeme) {
				case PRINT: return TokenType.PRINT;
			}
		}
		if(isOperator(lexeme)) {
			switch (lexeme) {
				case PLUS: return TokenType.PLUS;
				case MINUS: return TokenType.MINUS;
				case STAR: return TokenType.STAR;
				case SLASH: return TokenType.SLASH;
			}
			return TokenType.OPER;
		}
		if(isBracket(lexeme)) {
			switch (lexeme) {
				case LPT: return TokenType.LPT;
				case RPT: return TokenType.RPT;
				case LCB: return TokenType.LCB;
				case RCB: return TokenType.RCB;
				case LSB: return TokenType.LSB;
				case RSB: return TokenType.RSB;
			}
		}
		
		return TokenType.UNKN;
	}
	
	private static boolean isString(String lexeme) {
		return lexeme.startsWith("\"") && lexeme.endsWith("\"");
	}

	static boolean isEOL(String current) {
		return current.equals(";");
	}

	static boolean isDigit(String current) {
		return isThatTypeByRegEx("[0-9]+", current);
	}
	
	static boolean isID(String current) {
		if(isKeyword(current)) return false;
		return isThatTypeByRegEx("[a-zA-Z_]+", current);
	}
	
	private static boolean isKeyword(String current) {
		return KEY_WORDS.indexOf(current) != -1;
	}

	public static boolean isBracket(String current) {
		return BRACKETS.contains(current);
	}
	
	public static boolean isOperator(String current) {
		if(current.length() == 1) 
			return OPERATORS.contains(current);
		return isThatTypeByRegEx("\\*\\/\\+\\-", current);
	}
	
	static boolean isWhiteSpace(String current) {
		return isThatTypeByRegEx("\\s", current);
	}
	
	static boolean isAlloc(String current) {
		return current.equals("=");
	}
	
	static boolean isThatTypeByRegEx(String regEx, String current) {
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(current);
		return m.matches();
	}

	public static boolean isSameType(String current_character, String next_character) {
		TokenType type = getLexemeType(current_character);
		if(type.equals(TokenType.BRACKET)) 
			return false;
		return type.equals(getLexemeType(next_character));
	}
	
	public static String remove_comments_and_spaces(String context) {
		return context.replaceAll("#[^\\n\\r]+", ""). //one line comments with #
				replaceAll("\\/\\*[\\s\\S]*?\\*\\/", "").//multiline comments with /* COMMENT */
				replaceAll("\\n|\\t", ""). // 
				replaceAll("\\s+(?=([^\"]*\"[^\"]*\")*[^\"]*$)", "");//all whitespaces that are not in quotes
	}
}

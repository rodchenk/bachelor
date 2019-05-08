package analyzer;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LexerUtility {

	static final String LPT = "(", RPT = ")", LCB = "{", RCB = "}", LSB = "[", RSB = "]";
	final static List<String> OPERATORS = Arrays.asList("+", "-", "*", "/");
	final static List<String> BRACKETS = Arrays.asList(LPT, RPT, LSB, RCB, LSB, RSB);

	static TokenType getLexemeType(String lex) {
		String lexeme = lex.trim();
		
		if(isID(lexeme)) 		return TokenType.ID;
		if(isDigit(lexeme)) 	return TokenType.NUMBER;
		if(isOperator(lexeme)) 	return TokenType.OPER;
		if(isEOL(lexeme)) 		return TokenType.EOL;
		if(isString(lexeme))	return TokenType.TEXT;
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
		return isThatTypeByRegEx("[a-zA-Z]+", current);
	}
	
	static boolean isBracket(String current) {
		return BRACKETS.contains(current);
	}
	
	static boolean isOperator(String current) {
		if(current.length() == 1) 
			return OPERATORS.contains(current);
		return isThatTypeByRegEx("\\*\\/\\+\\-", current);
	}
	
	static boolean isWhiteSpace(String current) {
		return isThatTypeByRegEx("\\s", current);
	}
	
	static boolean isThatTypeByRegEx(String regEx, String current) {
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(current);
		return m.matches();
	}

	protected static boolean isSameType(String current_character, String next_character) {
		TokenType type = getLexemeType(current_character);
		if(type.equals(TokenType.BRACKET)) 
			return false;
		return type.equals(getLexemeType(next_character));
	}
}

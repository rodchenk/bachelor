package analyzer;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LexerUtility {

	final static List<String> OPERATORS = Arrays.asList("+", "-", "*", "/");
	
	static TokenType getLexemeType(String lex) {
		String lexeme = lex.trim();
		if(isString(lexeme)) 	return TokenType.STRING;
		if(isDigit(lexeme)) 	return TokenType.NUMBER;
		if(isOperator(lexeme)) 	return TokenType.OPERATOR;
		if(isEOL(lexeme)) 		return TokenType.EOL;
		
		return TokenType.UNKNOWN;
	}
	
	static boolean isEOL(String current) {
		return isThatTypeByRegEx("$", current);
	}

	static boolean isDigit(String current) {
		return isThatTypeByRegEx("[0-9]+", current);
	}
	
	static boolean isString(String current) {
		return isThatTypeByRegEx("[a-zA-Z]+", current);
	}
	
	static boolean isOperator(String current) {
		if(current.length() == 1) 
			return OPERATORS.contains(current);
		
		return isThatTypeByRegEx("\\.\\,\\+\\-", current);
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
		return getLexemeType(current_character).equals(getLexemeType(next_character));
	}
}

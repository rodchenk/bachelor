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
						PRINT = "print", ALLOC = "=";
	
	final static List<String> OPERATORS = Arrays.asList(PLUS, MINUS, STAR, SLASH, ALLOC);
	
	public static boolean isOperator(String current) {
		if(current.length() == 1) 
			return OPERATORS.contains(current);
		return isThatTypeByRegEx("\\*\\/\\+\\-\\=", current);
	}
	
	public static boolean isOperator(char current) {
		return isOperator(String.valueOf(current));
	}
		
	static boolean isThatTypeByRegEx(String regEx, String current) {
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(current);
		return m.matches();
	}
	
	public static String remove_comments_and_spaces(String context) {
		return context.replaceAll("#[^\\n\\r]+", ""). //one line comments with #
				replaceAll("\\/\\*[\\s\\S]*?\\*\\/", "").//multiline comments with /* COMMENT */
				replaceAll("\\n|\\t", ""). // 
				replaceAll("\\s+(?=([^\"]*\"[^\"]*\")*[^\"]*$)", "");//all whitespaces that are not in quotes
	}
}

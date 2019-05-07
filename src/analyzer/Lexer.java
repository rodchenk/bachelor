package analyzer;

import java.util.ArrayList;
import java.util.List;

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
		while(hasNext()) {
			char ch = next();
			String tok_value = String.valueOf(ch);
			this.tok_list.add(new Token(TokenType.NUMBER, tok_value));
		}
		return tok_list;
	}
	
	private boolean hasNext() {
		return position < context_length;
	}
	
	private char next() {
		return context.charAt(position++);
	}
}

package analyzer;

public class Token {
	
	private TokenType tok_type;
	private String tok_val;
	
	public Token(TokenType type, String value) {
		this.tok_type = type;
		this.tok_val = value;
	}
	
	public TokenType getType() {
		return this.tok_type;
	}
	
	public String getValue() {
		return this.tok_val;
	}
	
	@Override
	public String toString() {
		return String.format("%s with value %s", this.tok_type, this.tok_val);
	}
}

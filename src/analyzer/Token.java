package analyzer;

/**
 * @author M.Rodchenkov
 * @since version 0.1
 * @category Definition vom Token. Besteht aus Type (TokenType - enum) und Value (String)
 */

public class Token {
	
	private TokenType tok_type;
	private String tok_val;
	
	/**
	 * @param type {@link TokenType}
	 * @param value {@link String}
	 * Initialisation von 2 Bestandteilen - TokenType und TokenValue
	 */
	public Token(TokenType type, String value) {
		this.tok_type = type;
		this.tok_val = value;
	}
	
	/**
	 * @return TokenType {@link TokenType}
	 */
	public TokenType getType() {
		return this.tok_type;
	}
	
	/**
	 * @return Value {@link String}
	 */
	public String getValue() {
		return this.tok_val;
	}
	
	@Override
	public String toString() {
		return String.format("%s %c -----with value-----%c (%s)", this.tok_type, '\t', '\t', this.tok_val);
	}
}

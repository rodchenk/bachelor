package mir.analyzer.ast;

import mir.analyzer.TokenType;
import mir.lib.NumberValue;
import mir.lib.Value;

public class BinaryExpression implements Expression{
	
	private Expression exp1, exp2;
	private TokenType operator;
	
	public BinaryExpression(TokenType operator, Expression exp1, Expression exp2) {
		this.exp1 = exp1;
		this.exp2 = exp2;
		this.operator = operator;
	}
	
	@Override
	public Value eval() {
		final double value_1 = exp1.eval().asDouble();
		final double value_2 = exp2.eval().asDouble();
		switch (operator) {
			case PLUS: 	return new NumberValue(value_1 + value_2);
			case MINUS: return new NumberValue(value_1 - value_2);
			case SLASH: return new NumberValue(value_1 / value_2);
			case STAR: 	return new NumberValue(value_1 * value_2);
			default: 
				throw new RuntimeException("Unknown expression");
		}
	}
	
	@Override
	public String toString() {
		return String.format("%s %s %s", exp1.toString(), operator, exp2.toString());
	}
}

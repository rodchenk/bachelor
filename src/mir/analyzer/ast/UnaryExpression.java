package mir.analyzer.ast;

import mir.analyzer.TokenType;
import mir.lib.NumberValue;
import mir.lib.Value;

public class UnaryExpression implements Expression{

	private Expression exp1;
	private TokenType operator;
	
	public UnaryExpression(TokenType operator, Expression exp1) {
		this.exp1 = exp1;
		this.operator = operator;
	}
	
	@Override
	public Value eval() {
		double value = exp1.eval().asDouble();
		switch (operator) {
			case MINUS: return new NumberValue(-value);
			case STAR: 	return new NumberValue(Math.pow(value, 2));
			case SLASH: return new NumberValue(Math.pow(value, 0.5));
			case PLUS:
			default: 
				return exp1.eval();
		}
	}
	
	@Override
	public String toString() {
		return String.format("%s %s", operator, exp1.toString());
	}
}

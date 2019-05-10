package analyzer.ast;

import analyzer.TokenType;

public class BinaryExpression implements Expression{
	
	private Expression exp1, exp2;
	private TokenType operator;
	
	public BinaryExpression(TokenType operator, Expression exp1, Expression exp2) {
		this.exp1 = exp1;
		this.exp2 = exp2;
		this.operator = operator;
	}
	
	@Override
	public double eval() {
		switch (operator) {
			case PLUS: 	return exp1.eval() + exp2.eval();
			case MINUS: return exp1.eval() - exp2.eval();
			case SLASH: return exp1.eval() / exp2.eval();
			case STAR: 	return exp1.eval() * exp2.eval();
			default: 
				throw new RuntimeException("Unknown expression");
		}
	}
	
	@Override
	public String toString() {
		return String.format("%s %s %s", exp1.toString(), operator, exp2.toString());
	}
}

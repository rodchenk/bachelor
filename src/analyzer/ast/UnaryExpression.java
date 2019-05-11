package analyzer.ast;

import analyzer.TokenType;

public class UnaryExpression implements Expression{

	private Expression exp1;
	private TokenType operator;
	
	public UnaryExpression(TokenType operator, Expression exp1) {
		this.exp1 = exp1;
		this.operator = operator;
	}
	
	@Override
	public double eval() {
		switch (operator) {
			case MINUS: return -exp1.eval();
			case STAR: 	return Math.pow(exp1.eval(), 2);
			case SLASH: return Math.pow(exp1.eval(), 0.5);
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

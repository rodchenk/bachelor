package analyzer.ast;

import analyzer.Token;

public class NumberExpression implements Expression{

	private String expression_value;
	
	public NumberExpression(Token current_token) {
		this.expression_value = current_token.getValue();
	}
	
	@Override
	public double eval() {
		return Double.parseDouble(expression_value);
	}
	
	@Override
	public String toString() {
		return expression_value;
	}

}

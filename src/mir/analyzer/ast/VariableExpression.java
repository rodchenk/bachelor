package mir.analyzer.ast;

import mir.analyzer.Token;
import mir.lib.Variable;

public class VariableExpression implements Expression{
	
	private final double expression_value;
	private final String name;
	
	public VariableExpression(Token current_token) {
		this.name = current_token.getValue();
		expression_value = Variable.getVariable(this.name);
	}

	public VariableExpression(Token current_token, NumberExpression numberExpression) {
		this.name = current_token.getValue();
		this.expression_value = numberExpression.eval();
		Variable.setVariable(name, numberExpression);
	}

	@Override
	public double eval() {
		return expression_value;
	}

	@Override
	public String toString() {
		return String.format("%s", name);
	}
}

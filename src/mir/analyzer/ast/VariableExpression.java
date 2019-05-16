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

	@Override
	public double eval() {
		return expression_value;
	}

	@Override
	public String toString() {
		return String.format("%s(%d)", name, (long)(expression_value));
	}
}

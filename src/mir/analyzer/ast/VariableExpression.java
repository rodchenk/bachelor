package mir.analyzer.ast;

import mir.analyzer.Token;
import mir.lib.Value;
import mir.lib.Memory;

public class VariableExpression implements Expression{
	
	private final String name;
	
	public VariableExpression(Token current_token) {
		this.name = current_token.getValue();
	}

	@Override
	public Value eval() {
		return Memory.getVariable(name);
	}

	@Override
	public String toString() {
		return String.format("%s", name);
	}
}

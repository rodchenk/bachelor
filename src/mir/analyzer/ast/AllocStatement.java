package mir.analyzer.ast;

import mir.lib.VariableContainer;

public class AllocStatement implements Statement{
	
	private final String name;
	private Expression expression;
	
	public AllocStatement(String name, Expression expression) {
		this.name = name;
		this.expression = expression;
	}

	@Override
	public void execute() {
			VariableContainer.setVariable(name, expression.eval());
	}
	
	@Override
	public String toString() {
		return String.format("%s = %s", name, expression);
	}
}

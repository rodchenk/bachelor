package mir.analyzer.ast;

import mir.lib.Variable;

public class AllocStatement implements Statement{
	
	private final String name;
	private final Expression expression;
	
	public AllocStatement(String name, Expression expression) {
		this.name = name;
		this.expression = expression;
	}
	
	@Override
	public void execute() {
		Variable.setVariable(name, expression);
	}
	
	@Override
	public String toString() {
		return String.format("%s -> %f", name, expression.eval());
	}
}

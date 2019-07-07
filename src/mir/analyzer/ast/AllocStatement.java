package mir.analyzer.ast;

import mir.lib.VariableContainer;

public class AllocStatement implements Statement{
	
	private final String name;
	private final boolean _new;
	private Expression expression;
	
	public AllocStatement(String name, Expression expression) {
		this(false, name, expression);
	}

	public AllocStatement(boolean _new, String name, Expression expression) {
		this.name = name;
		this.expression = expression;
		this._new = _new;
	}

	@Override
	public void execute() {
		VariableContainer.setVariable(_new, name, expression.eval());
	}
	
	@Override
	public String toString() {
		return String.format("%s = %s", name, expression);
	}
}

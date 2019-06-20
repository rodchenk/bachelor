package mir.analyzer.ast;

import mir.lib.VariableContainer;
import mir.lib.Variable;

public class AllocStatement implements Statement{
	
	private final String name;
	private Expression expression;
	private Variable variable;
	
	public AllocStatement(String name, Expression expression) {
		this.name = name;
		this.expression = expression;
	}
	
	public AllocStatement(String value, Variable variable) {
		this.name = value;
		this.variable = variable;
	}

	@Override
	public void execute() {
		if(variable == null) 
			VariableContainer.setVariable(name, expression.eval());
	}
	
	@Override
	public String toString() {
		return variable == null ?
			String.format("%s = %s", name, expression) :
			String.format("%s %s = %s", variable.getModifiers().get("data_type"), name, variable.getExpression().asString());
	}
}

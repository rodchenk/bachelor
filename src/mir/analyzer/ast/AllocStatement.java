package mir.analyzer.ast;

import mir.lib.Memory;
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
		if(variable == null) {
			Memory.setVariable(name, expression);
		}else {
			Memory.create(name, variable);
		}
	}
	
	@Override
	public String toString() {
		return variable == null ?
			String.format("%s = %s", name, expression) :
			String.format("%s %s = %s", variable.getModifiers().get("data_type"), name, variable.getExpression().eval().asString());
	}
}

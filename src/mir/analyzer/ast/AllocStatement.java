package mir.analyzer.ast;

import mir.analyzer.TokenType;
import mir.lib.Variable;

public class AllocStatement implements Statement{
	
	private final String name;
	private final Expression expression;
	private TokenType type;
	
	public AllocStatement(String name, Expression expression) {
		this.name = name;
		this.expression = expression;
		this.type = null;
	}
	
	public AllocStatement(String value, TokenType type, Expression expression) {
		this.name = value;
		this.expression = expression;
		this.type = type;
	}

	@Override
	public void execute() {
		if(type == null) {
			Variable.setVariable(name, expression);
		}else {
			Variable.create(name, type, expression);
		}
	}
	
	@Override
	public String toString() {
		return String.format("%s = %s", name, expression);
	}
}

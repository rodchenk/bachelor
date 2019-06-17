package mir.lib;

import java.util.HashMap;
import java.util.Map;

import mir.analyzer.TokenType;
import mir.analyzer.ast.Expression;

public class Variable {

	private Map<String, TokenType> modifiers = new HashMap<>();
	private Expression expression;

	public Variable() {
		modifiers.put("data_type", null);
		modifiers.put("visibility", null);
		modifiers.put("constant", null);
	}
	
	public void setExpression(Expression expression) {
		this.expression = expression;
	}
	
	public Expression getExpression() {
		return this.expression;
	}
	
	public void setModifiers(Map<String, TokenType> modifiers) {
		this.modifiers = modifiers;
	}
	
	public Map<String, TokenType> getModifiers(){
		return this.modifiers;
	}
}

package mir.lib;

import java.util.HashMap;
import java.util.Map;

import mir.analyzer.TokenType;
import mir.analyzer.ast.Expression;

public class Variable {

	private Map<String, TokenType> modifiers = new HashMap<>();
	private Value expression;

	public Variable() {
		modifiers.put("data_type", null);
		modifiers.put("visibility", null);
		modifiers.put("constant", null);
	}
	
	public void setExpression(Value expression) {
		this.expression = expression;
	}
	
	public Value getExpression() {
		return this.expression;
	}
	
	public void setModifiers(Map<String, TokenType> modifiers) {
		this.modifiers = modifiers;
	}
	
	public Map<String, TokenType> getModifiers(){
		return this.modifiers;
	}
}

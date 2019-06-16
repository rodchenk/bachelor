package mir.lib;

import java.util.HashMap;
import java.util.Map;

import mir.analyzer.TokenType;
import mir.analyzer.ast.Expression;

public class Variable {
	
	private static Map<String, VariableProvider> variable_map = new HashMap<>();

	public static Value getVariable(String name) {
		VariableProvider v = variable_map.get(name);
		if(null != v) 
			return v.get();	
		else
			throw new RuntimeException("Variable " + name + " does not exist");
	}

	public static void setVariable(String name, Expression value) {
		System.out.println("Setting variable " + name + " to " + value.eval());
		VariableProvider v = variable_map.get(name);
		if(null != v)
			v.set(value.eval());
		else
			throw new RuntimeException("Variable " + name + " does not exist");
	}

	public static void create(String name, TokenType type, Expression expression) {
		if(!variable_map.containsKey(name)) {
			System.out.println("Creating variable " + name + " of type " + type);

			Value value = expression.eval();
			if(type.equals(value.type())) {
				variable_map.put(name, new VariableProvider(type, expression));
			}else {
				throw new RuntimeException(String.format("Variable %s does not match to type %s", name, type));
			}
		}
		else
			throw new RuntimeException(String.format("Variable %s of type %s already exists", name, type));
	}	
}

class VariableProvider{
	private TokenType type;
	private Value value;
	
	VariableProvider(TokenType type, Expression expression){
		this.type = type;
		this.value = expression.eval();
	}
	
	Value get() {
		return value;
	}
	
	void set(Value value) {
		this.value = value;
	}
	
	boolean is(TokenType type) {
		return type.equals(this.type);
	}
}
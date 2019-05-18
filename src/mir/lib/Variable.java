package mir.lib;

import java.util.HashMap;
import java.util.Map;

import mir.analyzer.ast.Expression;

public class Variable {

	static Map<String, Double> map = new HashMap<>();

	public static double getVariable(String name) {
		try {
			return map.get(name);
		}catch(NullPointerException e) {
			throw new RuntimeException("Variable " + name + " does not exist");
		}
	}

	public static void setVariable(String name, Expression value) {
		map.put(name, value.eval());
	}
}

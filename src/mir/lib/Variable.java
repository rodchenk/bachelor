package mir.lib;

import java.util.HashMap;
import java.util.Map;

import mir.analyzer.ast.NumberExpression;

public class Variable {

	static Map<String, Double> map = new HashMap<>();

	public static double getVariable(String name) {
		try {
			return map.get(name);
		}catch(NullPointerException e) {
			return 0;//TODO
		}
	}

	public static void setVariable(String name, NumberExpression value) {
		map.put(name, value.eval());
	}
}

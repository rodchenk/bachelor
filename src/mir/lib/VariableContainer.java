package mir.lib;

import java.util.HashMap;
import java.util.Map;

public class VariableContainer {
	
	private static final Map<String, Value> variable_map = new HashMap<>();
	
	public static Value getVariable(String name) {
		Value v = variable_map.get(name);
		if(null != v) 
			return v;
		else
			throw new RuntimeException("Variable " + name + " does not exist");
	}
	
	public static void setVariable(String name, Value value) {
		System.err.println("suka huli suda ne zahodit");
		variable_map.forEach((key, val) -> System.out.println(key + ": " + val.asString()));
		Value v = variable_map.get(name);
			variable_map.put(name, value);
	}
	
}
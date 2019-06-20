package mir.lib;

import java.util.HashMap;
import java.util.Map;

public class FunctionContainer {
	
	private static final Map<String, Function> function_map = new HashMap<>();
	
	static{
		function_map.put("test", (Function) (Value...values) -> {
			return new StringValue("Test function");
		});
		
		function_map.put("pow", (Function) (Value...values) -> {
			if(values.length != 2) throw new RuntimeException("Function pow() expects two arguments");
			return new NumberValue(Math.pow(values[0].asDouble(), values[1].asDouble()));
		});
	}
	
	public static Function getFunction(String name) {
		Function def = function_map.get(name);
		if(null != def) 
			return def;
		else
			throw new RuntimeException("Function " + name + " does not exist");
	}
	
	public static void addFunction(String name, Function def) {
		function_map.put(name, def);
	}
}

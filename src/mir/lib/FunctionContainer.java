package mir.lib;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class FunctionContainer {
	
	private static Map<String, Function> function_map = new HashMap<>();

	public static final Value _void = new BooleanValue(true); // TODO
	
	static{

		function_map.put("pow", (Function) (Value...values) -> {
			if(values.length != 2) throw new RuntimeException("Function pow() expects two arguments");
			return new NumberValue(Math.pow(values[0].asDouble(), values[1].asDouble()));
		});
		
		function_map.put("println", (Function) (Value...values) -> {
			for(Value value: values) System.out.println(value.asString());
			return _void;
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

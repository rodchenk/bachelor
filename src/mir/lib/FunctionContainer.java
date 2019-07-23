package mir.lib;

import java.util.HashMap;
import java.util.Map;

public class FunctionContainer {
	
	private static Map<String, Function> function_map = new HashMap<>();

	public static final Value _void = new BooleanValue(true); // TODO
	
	static{

		function_map.put("pow", (Function) (Value...values) -> {
			if(values.length != 2) throw new RuntimeException("Function pow() expects two arguments");
			return new NumberValue(Math.pow(values[0].asDouble(), values[1].asDouble()));
		});
		
		function_map.put("size_of", (Function) (Value...values) -> {
			if(values.length != 1) throw new RuntimeException("Function 'size_of' expects 1 argument");
			return new NumberValue(values[0].length());
		});
		
		function_map.put("split", (Function) (Value...values) -> {
			if(values.length != 2) throw new RuntimeException("Function 'split(Value, String)' expects 2 arguments");
			if(values[0] instanceof StringValue == false) {
				values[0] = new StringValue(values[0].asString());
			}
			return new ArrayValue( ((StringValue) values[0]).split(values[1]));
		});
		
		function_map.put("same", (Function) (Value...values) -> {
			if(values.length != 2) throw new RuntimeException("Function 'same(Object, Object)' expects 2 arguments");
			return new BooleanValue(values[0].hash() == values[1].hash());
		});
		
		function_map.put("get_time", (Function) (Value...values) -> {
			if(values.length != 0) throw new RuntimeException("Function 'get_time()' does not have any arguments");
			long var = System.currentTimeMillis();
			return new NumberValue(var);
		});
		
		function_map.put("get_file", (Function) (Value...values) -> {
			return null;
		});
		
		function_map.put("console_in", (Function) (Value...values) -> {
			return null;
		});
		
		function_map.put("str_to_num", (Function) (Value...values) -> {
			return new NumberValue(values[0].asDouble());
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

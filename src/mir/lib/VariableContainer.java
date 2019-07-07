package mir.lib;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class VariableContainer {
	
	private static Map<String, Value> variable_map = new HashMap<>();
	private static final Stack<Map<String, Value>> stack = new Stack<>();
	
	public static void push() {
		stack.push(new HashMap<>(variable_map));
	}
	
	public static void pop() {
		variable_map = stack.pop();
	}
	
	public static Value getVariable(String name) {
		Value v = variable_map.get(name);
		if(null != v) 
			return v;
		else
			throw new RuntimeException("Variable " + name + " does not exist");
	}
	
	public static void setVariable(boolean _new, String name, Value value) {
		boolean exists = variable_map.containsKey(name);
		if(_new && exists) {
			throw new RuntimeException("Variable " + name + " already exists");
//		}else if(!_new && !exists){
//			throw new RuntimeException("Variable " + name + " does not exist");
// 		TODO
		}else {
			variable_map.put(name, value);
		}
	}
}
package mir.lib;

import java.util.HashMap;
import java.util.Map;

public class Variable {
	static Map<String, Double> map = new HashMap<>();
	
	static{
		map.put("var", 14d);//test
	}
	
	public static double getVariable(String name) {
		try {
			return map.get(name);
		}catch(NullPointerException e) {
			return 0;//TODO
		}
	}
}

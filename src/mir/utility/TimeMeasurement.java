package mir.utility;

import java.util.HashMap;
import java.util.Map;

public class TimeMeasurement {
	
	private static Map<String, Long> table = new HashMap<>();
	
	public static void setMeasurement(String name) {
		table.put(name, System.currentTimeMillis());
	}
	
	public static long getResult(String name) {
		long now = System.currentTimeMillis();
		return now - table.get(name);
	}
}

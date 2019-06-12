package mir.lib;

public class StringValue implements Value{
	
	final private String value;
	
	public StringValue(String value) {
		this.value = value;
	}

	@Override
	public String asString() {
		return value;
	}

	@Override
	public double asDouble() {
		try {
			return Double.valueOf(value);
		}catch(NumberFormatException e) {
			throw new RuntimeException("Cannot parse string " + value + " to double");
		}
	}
	
	@Override
	public String toString() {
		return "hello from StringValue";
	}

}

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
		return value;
	}

	@Override
	public int length() {
		return this.value.length();
	}

	public Value[] split(Value expression) {
		String[] arr = this.value.split(expression.asString());
		Value[] array  = new Value[arr.length];
		for(int i = 0; i < array.length; i++) 
			array[i] = new StringValue(arr[i]);
		return array;
	}

	@Override
	public int hash() {
		return this.hashCode();
	}
}
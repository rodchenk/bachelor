package mir.lib;

public class BooleanValue implements Value{

	private final boolean value;
	
	public BooleanValue(boolean value) {
		this.value = value;
	}
	
	public BooleanValue(int value) {
		this(value == 1);
	}
	
	public BooleanValue(String value) {
		this(Boolean.valueOf(value));
	}
	
	@Override
	public String asString() {
		return String.valueOf(value);
	}

	@Override
	public double asDouble() {
		return value ? 1 : 0;
	}

}

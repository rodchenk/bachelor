package mir.lib;

public class BooleanValue implements Value{

	private boolean value;
	
	public static final BooleanValue TRUE = new BooleanValue(1);
	public static final BooleanValue FALSE = new BooleanValue(0);
	
	public BooleanValue(boolean value) {
		this.value = value;
	}

	public void set(boolean value) {
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

package mir.lib;

import java.util.Arrays;

public class ArrayValue implements Value{
	
	private final Value[] elements;

	public ArrayValue(Value[] elements) {
		int length = elements.length;
		this.elements = new Value[length];
		System.arraycopy(elements, 0, this.elements, 0, length);
	}
	
	public ArrayValue(int size) {
		this.elements = new Value[size];
	}
	
	public ArrayValue(ArrayValue array) {
		this(array.elements);
	}
	
	public Value get(int index) {
		return this.elements[index];
	}
	
	public void set(int index, Value value) {
		this.elements[index] = value;
	}
	
	@Override
	public int length() {
		return this.elements.length;
	}

	@Override
	public String asString() {
		return Arrays.toString(this.elements);
	}
	

	@Override
	public double asDouble() {
		throw new RuntimeException("Can't parse Array as number");
	}

	@Override
	public int hash() {
		return this.hashCode();
	}

}

package mir.lib;

import mir.analyzer.TokenType;

public class NumberValue implements Value{
	
	final double value;
	
	public NumberValue(double value) {
		this.value = value;
	}

	@Override
	public String asString() {
		return String.valueOf(value);
	}

	@Override
	public double asDouble() {
		return value;
	}
	
	@Override
	public String toString() {
		return asString();
	}

	@Override
	public TokenType type() {
		return TokenType.NUM;
	}
}

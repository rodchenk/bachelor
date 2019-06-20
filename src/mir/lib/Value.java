package mir.lib;

import mir.analyzer.TokenType;

public interface Value {
	
	String asString();
	
	double asDouble();
	
	TokenType type();
	
}

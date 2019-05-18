package mir.analyzer.ast;

import mir.lib.NumberValue;
import mir.lib.StringValue;
import mir.lib.Value;

public class ValueExpression implements Expression{

	private Value value;
		
	public ValueExpression(String value) {
		this.value = new StringValue(value);
	}
	
	public ValueExpression(double value) {
		this.value = new NumberValue(value);
	}
	
	@Override
	public Value eval() {
		return value;
	}
	
	@Override
	public String toString() {
		return value.toString();
	}

}

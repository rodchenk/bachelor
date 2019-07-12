package mir.analyzer.ast;

import mir.lib.ArrayValue;
import mir.lib.Value;
import mir.lib.VariableContainer;

public class ArrayAccessExpression implements Expression {

	private final String array;
	private final Expression index;
	
	public ArrayAccessExpression(String name, Expression index) {
		this.array = name;
		this.index = index;
	}

	@Override
	public Value eval() {
		Value arr = VariableContainer.getVariable(array);
		if(null != arr && arr instanceof ArrayValue) {
			ArrayValue _array = (ArrayValue) arr;
			return _array.get((int) this.index.eval().asDouble());
		}else {
			throw new RuntimeException("Array does not exist or variable " + this.array + " is not an array");
		}
	}
}

package mir.analyzer.ast;

import mir.lib.ArrayValue;
import mir.lib.Value;
import mir.lib.VariableContainer;

public class ArrayAllocStatement implements Statement{
	
	private final String array;
	private final Expression index;
	private final Expression value;
	
	public ArrayAllocStatement(String array, Expression index, Expression value) {
		this.array = array;
		this.index = index;
		this.value = value;
	}
	@Override
	public void execute() {
		Value arr = VariableContainer.getVariable(array);
		if(null != arr && arr instanceof ArrayValue) {
			ArrayValue _array = (ArrayValue) arr;
			_array.set((int) this.index.eval().asDouble(), this.value.eval());
		}else {
			throw new RuntimeException("Array does not exist or variable " + this.array + " is not an array");
		}
	}
}

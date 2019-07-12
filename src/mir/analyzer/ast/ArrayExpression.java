package mir.analyzer.ast;

import java.util.List;

import mir.lib.ArrayValue;
import mir.lib.Value;

public class ArrayExpression implements Expression {

	private final List<Expression> elements;
	
	public ArrayExpression(List<Expression> elements) {
		this.elements = elements;
	}
	
	@Override
	public Value eval() {
		int size = this.elements.size();
		ArrayValue array = new ArrayValue(size);
		for(int i = 0; i < size; i++) {
			array.set(i, this.elements.get(i).eval());
		}
		return array;
	}


}

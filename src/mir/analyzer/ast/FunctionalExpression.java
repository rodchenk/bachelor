package mir.analyzer.ast;

import java.util.ArrayList;
import java.util.List;

import mir.lib.FunctionContainer;
import mir.lib.Value;

public class FunctionalExpression implements Expression{
	
	final private String name;
	final private List<Expression> args;
	
	public FunctionalExpression(String name) {
		this.name = name;
		this.args = new ArrayList<>();
	}

	public FunctionalExpression(String name, List<Expression> args) {
		this.name = name;
		this.args = args;
	}
	
	public void addArgs(Expression expression) {
		this.args.add(expression);
	}
	
	@Override
	public Value eval() {
		int size = args.size();
		Value[] values = new Value[size];
		for(int i = 0; i < size; i++) {
			values[i] = args.get(i).eval();
		}
		return FunctionContainer.getFunction(name).execute(values);
	}

}

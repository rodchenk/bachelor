package mir.analyzer.ast;

import java.util.ArrayList;
import java.util.List;

import mir.lib.Function;
import mir.lib.FunctionContainer;
import mir.lib.UserDefinedFunction;
import mir.lib.Value;
import mir.lib.VariableContainer;

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
		final Function def = FunctionContainer.getFunction(name);
		
		int size = args.size();
		Value[] values = new Value[size];
		for(int i = 0; i < size; i++) {
			values[i] = args.get(i).eval();
		}
		if(def instanceof UserDefinedFunction) {
			UserDefinedFunction _def = (UserDefinedFunction) def;
			if(size != _def.getArgSize()) 
				throw new RuntimeException("Invalid count of arguments");
			VariableContainer.push();
			for (int i = 0; i < size; i++) {
				VariableContainer.setVariable(_def.getArgName(i), values[i]);
			}
			Value result = _def.execute(values);
			VariableContainer.pop();
			return result;
		}
		return def.execute(values);
	}

}

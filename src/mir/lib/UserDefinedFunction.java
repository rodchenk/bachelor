package mir.lib;

import java.util.List;

import mir.analyzer.ast.ReturnStatement;
import mir.analyzer.ast.Statement;
import mir.lib.Function;
import mir.lib.Value;

public class UserDefinedFunction implements Function {
	
	private final List<String> args;
	private final Statement def_body;
	
	public UserDefinedFunction(List<String> args, Statement def_body) {
			this.args = args;
			this.def_body = def_body;
	}

	public int getArgSize() {
		return args.size();
	}
	
	public String getArgName(int position) {
		if(position < 0 || position >= getArgSize()) return "";
		return args.get(position);
	}
	
	@Override
	public Value execute(Value... values) {
		try {
			def_body.execute();
			return FunctionContainer._void;
		}catch(ReturnStatement _return) {
			return _return.getResult();
		}
	}

}

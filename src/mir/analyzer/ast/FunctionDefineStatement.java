package mir.analyzer.ast;

import java.util.List;

import mir.lib.FunctionContainer;
import mir.lib.UserDefinedFunction;

public class FunctionDefineStatement implements Statement{

	private final String name;
	private final List<String> args;
	private final Statement def_body;
	
	public FunctionDefineStatement(String name, List<String> args, Statement body) {
		this.name = name;
		this.args = args;
		this.def_body = body;
	}

	@Override
	public void execute() {
		FunctionContainer.addFunction(name, new UserDefinedFunction(args, def_body));
	}

}

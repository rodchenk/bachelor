package mir.analyzer.ast;

import mir.lib.Value;

public class ReturnStatement extends RuntimeException implements Statement{

	private static final long serialVersionUID = 1L;

	private final Expression _return;
	private Value result;
	
	public ReturnStatement(Expression _return) {
		this._return = _return;
	}
	
	public Value getResult() {
		return this.result;
	}

	@Override
	public void execute() {
		this.result = _return.eval();
		throw this;
	}
}

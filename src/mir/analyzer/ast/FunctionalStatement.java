package mir.analyzer.ast;

public class FunctionalStatement implements Statement {

	private final Expression def_expression;
	
	public FunctionalStatement(Expression expression) {
		this.def_expression = expression;
	}
	@Override
	public void execute() {
		this.def_expression.eval();
	}

}

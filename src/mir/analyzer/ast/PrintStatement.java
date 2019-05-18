package mir.analyzer.ast;

public class PrintStatement implements Statement{
	
	private final Expression expression;
	
	public PrintStatement(Expression expression) {
		this.expression = expression;
	}
	
	@Override
	public void execute() {
		System.out.print(this.expression.eval().asString());
	}
}

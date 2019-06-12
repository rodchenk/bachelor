package mir.analyzer.ast;

@SuppressWarnings("serial")
public class ContinueStatement extends RuntimeException implements Statement {

	@Override
	public void execute() {
		throw this;
	}

}

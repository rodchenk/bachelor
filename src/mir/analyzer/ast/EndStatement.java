package mir.analyzer.ast;

@SuppressWarnings("serial")
public class EndStatement extends RuntimeException implements Statement {
	
	@Override
	public void execute() {
		throw this;
	}
}

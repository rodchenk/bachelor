package mir.analyzer.ast;

public class ForStatement implements Statement {

	private final Statement instance;
	private final Expression condition;
	private final Statement increment;
	private final Statement _for;
	
	public ForStatement(Statement instance, Expression condition, Statement increment, Statement _for) {
		this.instance = instance;
		this.condition = condition;
		this.increment = increment;
		this._for = _for;
	}

	@Override
	public void execute() {
		for(instance.execute(); condition.eval().asDouble() == 1; increment.execute()) {
			try {
				_for.execute();
			}catch(EndStatement end) {
				break;
			}catch(ContinueStatement _continue) {
				continue;
			}
		}
	}
	
	@Override
	public String toString() {
		return String.format("for(%s;%s;%s){\n%s}", instance.toString(), condition.toString(), increment.toString(), _for.toString());
	}
}

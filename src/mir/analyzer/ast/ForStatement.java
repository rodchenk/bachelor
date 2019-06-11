package mir.analyzer.ast;

public class ForStatement implements Statement {

	private final AllocStatement instance;
	private final Expression condition;
	private final AllocStatement increment;
	private final Statement _for;
	
	public ForStatement(AllocStatement instance, Expression condition, AllocStatement increment, Statement _for) {
		this.instance = instance;
		this.condition = condition;
		this.increment = increment;
		this._for = _for;
	}

	@Override
	public void execute() {
		for(instance.execute(); condition.eval().asDouble() == 1; increment.execute()) {
			_for.execute();
		}
	}

}

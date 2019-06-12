package mir.analyzer.ast;

public class Iteratiiontatement implements Statement {
	
	private final Expression condition;
	private final Statement _while;
	
	public Iteratiiontatement(Expression condition, Statement _while) {
		this.condition = condition;
		this._while = _while;
	}
	
	@Override
	public void execute() {
		while(this.condition.eval().asDouble() == 1) {
			try {
				_while.execute();
			}catch(EndStatement end){
				break;
			}catch(ContinueStatement _continue) {
				continue;
			}
		}
	}
}

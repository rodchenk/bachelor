package mir.analyzer.ast;

import java.util.List;

public class Iteratiiontatement implements Statement {
	
	private final Expression condition;
	private final List<Statement> _while;
	
	public Iteratiiontatement(Expression condition, List<Statement> _while) {
		this.condition = condition;
		this._while = _while;
	}
	
	@Override
	public void execute() {
		//TODO
		while(condition.eval().asDouble() == 1){
			this._while.stream().forEach(Statement::execute);
		}
		
//		if(this.condition.eval().asDouble() == 1)
//			this._while.stream().forEach(Statement::execute);
	}

}

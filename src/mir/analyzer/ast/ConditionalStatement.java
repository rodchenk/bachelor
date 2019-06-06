package mir.analyzer.ast;

import java.util.List;

public class ConditionalStatement implements Statement{

	private final Expression condition;	
	private final List<Statement> _if, _else;

	public ConditionalStatement(Expression condition, List<Statement> _if, List<Statement> _else) {
		this.condition = condition;
		this._if = _if;
		this._else = _else;
	}

	@Override
	public void execute() {
		if(this.condition.eval().asDouble() == 1 ){
			this._if.stream().forEach(Statement::execute);
		}else if(this._else != null){
			this._else.stream().forEach(Statement::execute);
		}
	}
}

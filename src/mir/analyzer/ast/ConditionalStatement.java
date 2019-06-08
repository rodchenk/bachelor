package mir.analyzer.ast;

public class ConditionalStatement implements Statement{

	private final Expression condition;	
	private final Statement _if, _else;

	public ConditionalStatement(Expression condition, Statement _if, Statement _else) {
		this.condition = condition;
		this._if = _if;
		this._else = _else;
	}

	@Override
	public void execute() {
		if(this.condition.eval().asDouble() == 1 ){
			this._if.execute();
		}else if(this._else != null){
			this._else.execute();
		}
	}
}

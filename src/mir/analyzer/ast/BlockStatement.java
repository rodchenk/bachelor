package mir.analyzer.ast;

import java.util.ArrayList;
import java.util.List;

public class BlockStatement implements Statement{

	private final List<Statement> statements;
	
	public BlockStatement() {
		this.statements = new ArrayList<>();
	}
	
	public void add(Statement statement) {
		statements.add(statement);
	}
	
	@Override
	public void execute() {
		this.statements.forEach(Statement::execute);
	}
}

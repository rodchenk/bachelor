package analyzer.ast;

import java.util.ArrayList;
import java.util.List;

import analyzer.Token;
import static analyzer.TokenType.*;

public class Parser {
	
	final List<Token> tokens;
	final int size;
	int position;
	
	public Parser(List<Token> tokens) {
		this.tokens = tokens;
		size = tokens.size();
	}
	
	private Expression expression() {
		return add();
	}
	
	private Expression add() {
		Expression e = multiply();
		Token token;
		while(hasNext()) {
			token = getNext();
			if(token.hasType(MINUS)) {
				e = new BinaryExpression(MINUS, e, multiply());
				continue;
			}
			if(token.hasType(PLUS)) {
				e = new BinaryExpression(PLUS, e, multiply());
				continue;
			}
			break;
		}
		return e;
	}
	
	private Expression multiply() {
		Expression e = unary();
		Token token;
		while(hasNext()) {
			token = getNext();
			if(token.hasType(SLASH)) {
				e = new BinaryExpression(SLASH, e, multiply());
				continue;
			}
			if(token.hasType(STAR)) {
				e = new BinaryExpression(STAR, e, multiply());
				continue;
			}
			break;
		}
		return e;
	}
	
	private Expression unary() {

		return primary();
	}
	
	private Expression primary() {
		final Token current_token = getNext();
	    if (current_token.hasType(NUMBER)) {
	    	return new NumberExpression(current_token);
	    }

	    throw new RuntimeException("Unknown expression");
	}
	
	public List<Expression> parse() {
		List<Expression> expressions = new ArrayList<>();
		
		while(hasNext()) {
			expressions.add(expression());
		}

		return expressions;
	}
	
	private Token getNext() {
		return tokens.get(position++);
	}
	
	private boolean hasNext() {
		return position < size;
	}
}

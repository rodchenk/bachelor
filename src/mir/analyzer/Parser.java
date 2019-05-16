package mir.analyzer;

import java.util.ArrayList;
import java.util.List;

import mir.analyzer.ast.BinaryExpression;
import mir.analyzer.ast.Expression;
import mir.analyzer.ast.NumberExpression;
import mir.analyzer.ast.UnaryExpression;
import mir.analyzer.ast.VariableExpression;

import static mir.analyzer.TokenType.*;

public class Parser {
	
	final List<Token> tokens;
	final int size;
	int position;
	
	public Parser(List<Token> tokens) {
		this.tokens = tokens;
		size = tokens.size();
	}
	
	public List<Expression> parse() {
		List<Expression> expressions = new ArrayList<>();
		
		while(hasNext()) {
			expressions.add(expression());
		}

		return expressions;
	}
	
	private Expression expression() {
		return add();
	}
	
	private Expression add() {
		Expression e = multiply();
		Token token;
		while(!this.is(EOL)) {
			token = getTokenByRelativePosition(0);
			if(is(PLUS)) {
				e = new BinaryExpression(PLUS, e, multiply());
				continue;
			}
			if(is(MINUS)) {
				e = new BinaryExpression(MINUS, e, multiply());
				continue;
			}
			break;
		}
		return e;
	}
	
	private Expression multiply() {
		Expression e = unary();
		Token token;
		while(!this.is(EOL)) {
			token = getTokenByRelativePosition(0);
			if(this.is(STAR)) {
				e = new BinaryExpression(STAR, e, multiply());
				continue;
			}
			if(this.is(SLASH)) {
				e = new BinaryExpression(SLASH, e, multiply());
				continue;
			}
			break;
		}
		return e;
	}
	
	private Expression unary() {
		if (this.is(MINUS)) 
			return new UnaryExpression(MINUS, unary());
		if(this.is(STAR))
			return new UnaryExpression(STAR, unary());
		if(this.is(SLASH))
			return new UnaryExpression(SLASH, unary());
		return primary();
	}
	
	private Expression primary() {
		final Token current_token = getTokenByRelativePosition(0);
	    if (this.is(NUMBER)) {
	    	return new NumberExpression(current_token);
	    }
	    if(this.is(LPT)) {
	    	Expression result = expression();
            this.is(RPT);
            return result;
	    }
	    if(this.is(ID)) {
	    	return new VariableExpression(current_token); 
	    }

	    throw new RuntimeException("Unknown expression");
	}
	
	private Token getTokenByRelativePosition(int add_position) {
		int pos = this.position + add_position;
		if(hasTokenAt(pos))
			return tokens.get(pos);
		return new Token(TokenType.EOL);//TODO EOF
	}
	
	private boolean is(TokenType type) {
		Token token = this.getTokenByRelativePosition(0);
		if(!token.getType().equals(type)) 
			return false;
		this.position++;
		return true;
	}
	
	private boolean hasNext() {
		return position < size;
	}
	
	private boolean hasTokenAt(int i) {
		return i < size;
	}
}

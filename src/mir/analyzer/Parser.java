package mir.analyzer;

import java.util.ArrayList;
import java.util.List;

import mir.analyzer.ast.AllocStatement;
import mir.analyzer.ast.BinaryExpression;
import mir.analyzer.ast.ConditionalExpression;
import mir.analyzer.ast.Expression;
import mir.analyzer.ast.ValueExpression;
import mir.analyzer.ast.PrintStatement;
import mir.analyzer.ast.Statement;
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
	
	public List<Statement> parse() {
		List<Statement> statements = new ArrayList<>();
		
		while(hasNext()) {
			statements.add(statement());
		}

		return statements;
	}
	
	private Statement statement() {
		if(this.is(PRINT)) {
			return new PrintStatement(expression());
		}
//		if(this.is(IF)) {
//			return new IfStatement(expression());
//		}
//		
		return allocStatement();
	}
	
	private Statement allocStatement() {
		final Token current_token = this.getTokenByRelativePosition(0);
		if(this.is(ID) && this.is(ALLOC)) {	// x = ...
			return new AllocStatement(current_token.getValue(), expression());
		}
		
		throw new RuntimeException("Unknown statement:" + current_token.getType() + " " + current_token.getValue());
	}

	private Expression expression() {
		return condition();
	}
	
	private Expression condition() {
		Expression e = add();
		while(!this.is(EOL)) {
			if(is(LT)) {
				e = new ConditionalExpression(LT, e, add());
				continue;
			}
			if(is(GT)) {
				e = new ConditionalExpression(GT, e, add());
				continue;
			}
			break;
		}
		return e;
	}
	
	private Expression add() {
		Expression e = multiply();
		while(!this.is(EOL)) {
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
		while(!this.is(EOL)) {
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
	    final String token_value = getTokenByRelativePosition(0).getValue();
	    
		if (this.is(NUMBER)) {
	    	return new ValueExpression(Double.parseDouble(token_value));
	    }

	    if(this.is(TEXT)) {
	    	return new ValueExpression(token_value);
	    }
	    
	    if(this.is(LPT)) {
	    	Expression result = expression();
            this.is(RPT);
            return result;
	    }

	    return variable();
	}
	
	private Expression variable() {
		final Token current_token = getTokenByRelativePosition(0);
		
		if(this.is(ID)) {
			return new VariableExpression(current_token);
		}
		
		throw new RuntimeException("Unknown expression");
	}
	
	private Token getTokenByRelativePosition(int add_position) {
		int pos = this.position + add_position;
		if(hasTokenAt(pos))
			return tokens.get(pos);
		return new Token(TokenType.EOL);
	}
	
	/**
	 * @param type {@link TokenType} 
	 * @return boolean, true if given type equels to current (position will be increased), otherwise false
	 */
	private boolean is(TokenType type) {
		Token token = this.getTokenByRelativePosition(0);
		if(!token.getType().equals(type)) 
			return false;
		this.position++;
		return true;
	}
	
	/**
	 * @return boolean true if there is next character in program content, otherwise false
	 */
	private boolean hasNext() {
		return position < size;
	}
	
	/**
	 * @param i some char point of program context
	 * @return true if there is character at position <b>i</b> in program content, otherwise false
	 */
	private boolean hasTokenAt(int i) {
		return i < size;
	}
}
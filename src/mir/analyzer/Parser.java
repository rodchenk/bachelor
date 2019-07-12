package mir.analyzer;

import java.util.ArrayList;
import java.util.List;

import mir.analyzer.ast.AllocStatement;
import mir.analyzer.ast.ArrayAccessExpression;
import mir.analyzer.ast.ArrayAllocStatement;
import mir.analyzer.ast.ArrayExpression;
import mir.analyzer.ast.BinaryExpression;
import mir.analyzer.ast.BlockStatement;
import mir.analyzer.ast.ConditionalExpression;
import mir.analyzer.ast.ConditionalStatement;
import mir.analyzer.ast.ContinueStatement;
import mir.analyzer.ast.EndStatement;
import mir.analyzer.ast.Expression;
import mir.analyzer.ast.ForStatement;
import mir.analyzer.ast.FunctionDefineStatement;
import mir.analyzer.ast.FunctionalExpression;
import mir.analyzer.ast.FunctionalStatement;
import mir.analyzer.ast.Iteratiiontatement;
import mir.analyzer.ast.ValueExpression;
import mir.analyzer.ast.PrintStatement;
import mir.analyzer.ast.ReturnStatement;
import mir.analyzer.ast.Statement;
import mir.analyzer.ast.UnaryExpression;
import mir.analyzer.ast.VariableExpression;

import static mir.analyzer.TokenType.*;

public class Parser {
	
	final private List<Token> tokens;
	final private int size;
	private int position;
	
	public Parser(List<Token> tokens) {
		this.tokens = tokens;
		size = tokens.size();
	}
	
	public Statement parse() {
		BlockStatement statements = new BlockStatement();
		
		while(hasNext()) {
			statements.add(statement());
		} 

		return statements;
	}
	
	private Statement statement() {
		Token current_token = this.look(0);
		
		if(this.is(PRINT)) {
			return new PrintStatement(expression());
		}
		
		if(this.is(IF)) {
			final Expression condition = or();
			final Statement _if = getInheritedStatements(), 
							_else = this.is(ELSE) ? getInheritedStatements() : null;
			return new ConditionalStatement(condition, _if, _else);
		}
		
		if(this.is(WHILE)) {
			final Expression condition = or();
			final Statement _while = getInheritedStatements();
			return new Iteratiiontatement(condition, _while);
		}
		
		if(this.is(FOR)) {
			Statement instance;
			// for index = 0; index < 10; index = index + 1:
			// for index; index < 10 ...
			// for var: index = 1; index < ...
			boolean _new = is(VAR);
			if(_new) consume(COLON);
			
			current_token = look(0);
			if(look(1).getType().equals(ALLOC)) { // index = ..
				consume(ID);
				consume(ALLOC);
				instance =  new AllocStatement(_new, current_token.getValue(), expression());
			}else {
				instance =  new AllocStatement(_new, current_token.getValue(), expression());
			}

			consume(END);

			final Expression condition = or();
				
			consume(END);

			consume(ID);
			consume(ALLOC);
			final Statement increment = new AllocStatement(current_token.getValue(), expression());
			final Statement _for = getInheritedStatements();
			return new ForStatement(instance, condition, increment, _for);
		}
		
		if(this.is(DEF)) {
			return functionDefine();
		}
		
		if(current_token.getType().equals(ID) && look(1).getType().equals(LPT)) {
			return new FunctionalStatement(function());
		}
		
		if(is(VAR)) {
			consume(COLON);
			current_token = consume(ID);
			consume(ALLOC);
			return new AllocStatement(true, current_token.getValue(), expression());
		}

		if(lookMatch(0, ID) && lookMatch(1, ALLOC)) {
			consume(ID);
			consume(ALLOC);			
			return new AllocStatement(current_token.getValue(), expression());
		}
		
		if(lookMatch(0, ID) && lookMatch(1, LSB)) {
			// arr[1] = 100
			consume(ID);
			consume(LSB);
			Expression index = expression();
			consume(RSB);
			consume(ALLOC);
			return new ArrayAllocStatement(current_token.getValue(), index, expression());
		}
				
		if(this.is(RETURN)) return new ReturnStatement(expression());

		if(this.is(END)) return new EndStatement();
		
		if(this.is(CONTINUE)) return new ContinueStatement();
				
		throw new RuntimeException("Unknown statement:" + current_token.getType() + " " + current_token.getValue());
	}	
	
	private FunctionDefineStatement functionDefine() {
		final String name = consume(ID).getValue();
		consume(LPT);
		List<String> args = new ArrayList<>();
		while(!this.is(RPT)) {
			is(VAR);
			is(COLON);
			args.add(consume(ID).getValue());
			is(COMMA);
		}
		Statement def_body = getInheritedStatements();
		return new FunctionDefineStatement(name, args, def_body);
	}
	
	private Expression function() {
		final String name = look(0).getValue();
		FunctionalExpression def = new FunctionalExpression(name);
		consume(ID);
		consume(LPT);
		while(!this.is(RPT)) {
			def.addArgs(expression());
			is(COMMA);
		}
		return def;
	}

	private Expression expression() {
		return or();
	}
	
	private Expression or() {
		Expression expression = and();
		if(this.is(OR)) {
			return new ConditionalExpression(OR, expression, and());
		}
		return expression;
	}
	
	private Expression and() {
		Expression expression = condition();
		if(this.is(AND)) {
			return new ConditionalExpression(AND, expression, condition());
		}
		return expression;
	}
	
	private Expression condition() {
		Expression e = add();
		while(!is(EOL)) {
			if(is(LT)) {	
				e = new ConditionalExpression(LT, e, add()); 	
				continue; 
			}
			if(is(GT)) {	
				e = new ConditionalExpression(GT, e, add()); 
				continue; 
			}
			if(is(GTEQ)) {	
				e = new ConditionalExpression(GTEQ, e, add()); 	
				continue; 
			}
			if(is(LTEQ)) {	
				e = new ConditionalExpression(LTEQ, e, add()); 	
				continue; 
			}
			if(is(EQ)) {	
				e = new ConditionalExpression(EQ, e, add()); 	
				continue; 
			}
			if(is(NOTEQ)) { 
				e = new ConditionalExpression(NOTEQ, e, add()); 
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
			if(is(MODULO)) {
				e = new BinaryExpression(MODULO, e, multiply());
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
	    Token tokenByRelativePosition = look(0);
		final String token_value = tokenByRelativePosition.getValue();
	    
		if (is(NUMBER)) {
	    	return new ValueExpression(Double.parseDouble(token_value));
	    }

	    if(is(TRUE) || is(FALSE)) { // TODO
	    	return new ValueExpression(Boolean.valueOf(token_value));
	    }
	    
	    if(is(TEXT)) {
	    	return new ValueExpression(token_value);
	    }
	    
	    if(is(LPT)) {
	    	Expression result = expression();
            this.is(RPT);
            return result;
	    }
	    
	    return variable();
	}
	
	private Expression variable() {
		final Token current_token = look(0);
		
		if(current_token.getType().equals(ID) && lookMatch(1, LPT)) {
			return function();
		}
		
		if(current_token.getType().equals(ID) && lookMatch(1, LSB)) {
			return array_element();
		}
		
		if(lookMatch(0, LCB)) {
			// {0, 2, 4}
			List<Expression> elements = new ArrayList<>();
			consume(LCB);
			while(!this.is(RCB)) {
				elements.add(expression());
				is(COMMA);
			}
			return new ArrayExpression(elements);
		}
		
		if(this.is(ID)) {
			return new VariableExpression(current_token);
		}
		throw new RuntimeException("Unknown expression " + current_token.getType() + " with value " + current_token.getValue());
	}
	
	private Expression array_element() {
		String array_name = consume(ID).getValue();
		consume(LSB);
		Expression index = expression();
		consume(RSB);
		return new ArrayAccessExpression(array_name, index);
	}

	private Token look(int add_position) {
		int pos = this.position + add_position;
		if(hasTokenAt(pos))
			return tokens.get(pos);
		return new Token(TokenType.EOF);
	}
	
	private boolean lookMatch(int add_position, TokenType type) {
		return look(add_position).getType().equals(type);
	}
		
	/**
	 * Gibt einen Block von Statements zurueck. Z.B. fuer if(){...statements...}else{...statements...}
	 * @return List<Statement> 
	 */
	private Statement getInheritedStatements() {
		final BlockStatement block = new BlockStatement();
		is(COLON);
		if(this.is(LSB)) {
			while(!this.is(RSB)) 
				block.add(statement());
		}else {
			block.add(statement());
		}
		return block;
	}
	
	/**
	 * @param type {@link TokenType} 
	 * @return boolean, true if given type equels to current (position will be increased), otherwise false
	 */
	private boolean is(TokenType type) {
		Token token = this.look(0);
		if(!token.getType().equals(type)) 
			return false;
		this.position++;
		return true;
	}
	
	/**
	 * @throws RuntimeException when das erwartete Token nicht dem aktuellen entspricht
	 * @param type {@link TokenType} das zu erwartende Token
	 * @return {@link TokenType} das aktuelle Token
	 */
    private Token consume(TokenType type) {
        final Token token = look(0);
        if (!token.getType().equals(type)) {
            throw new RuntimeException("Token " + token + " doesn't match " + type);
        }
        this.position++;
        return token;
    }
	
	/**
	 * @return boolean true if there is next character in program content, otherwise false
	 */
	private boolean hasNext() {
		//return !getTokenByRelativePosition(0).getType().equals(EOF);
		return position < size;
	}
	
	/**
	 * @param i some char point of program context
	 * @return true if there is character at position <b>i</b> in program content, otherwise false
	 */
	private boolean hasTokenAt(int i) {
		return i < size && i >= 0;
	}
}

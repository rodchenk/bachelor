package mir.analyzer.ast;

import mir.analyzer.TokenType;
import mir.lib.BooleanValue;
import mir.lib.NumberValue;
import mir.lib.Value;

public class ConditionalExpression implements Expression{

	private Expression left_expression, right_expression;
	private TokenType operator;
	
	public ConditionalExpression(TokenType operator, Expression exp1, Expression exp2) {
		this.left_expression = exp1;
		this.right_expression = exp2;
		this.operator = operator;
	}
	
	@Override
	public Value eval() {
		final Value left = left_expression.eval();
		final Value right = right_expression.eval();
		
		if(left instanceof NumberValue && right instanceof NumberValue) {
			final double value_1 = left.asDouble();
			final double value_2 = right.asDouble();
			switch (operator) {
				case GT: return new BooleanValue(value_1 > value_2);
				case LT: return new BooleanValue(value_1 < value_2);
				case EQ: return new BooleanValue(value_1 == value_2);
				case LTEQ: return new BooleanValue(value_1 <= value_2);
				case GTEQ: return new BooleanValue(value_1 >= value_2);
				case NOTEQ:return new BooleanValue(value_1 != value_2);
				default: 
					throw new RuntimeException("Unknown operator (" + operator + ") for binary conditional expression");
			}
		}
		final String value_1 = left.asString();
		final String value_2 = right.asString();
		// string compare
		switch (operator) {
			case EQ: return new BooleanValue(value_1.equals(value_2)); // equels or hashCode ?
			case NOTEQ: return new BooleanValue(!value_1.equals(value_2)); // TODO compareTo()
			default:
				throw new RuntimeException("Unable to parse conditional expression with operator (" + operator + ")");
		}		
	}
	
	@Override
	public String toString() {
		return String.format("%s %s %s", left_expression.toString(), operator, right_expression.toString());
	}
}

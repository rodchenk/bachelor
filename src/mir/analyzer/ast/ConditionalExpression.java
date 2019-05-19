package mir.analyzer.ast;

import mir.analyzer.TokenType;
import mir.lib.NumberValue;
import mir.lib.StringValue;
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
				case GT: return new NumberValue(value_1 > value_2 ? 1 : 0); // 1 true, 0 false
				case LT: return new NumberValue(value_1 < value_2 ? 1 : 0);
				default: 
					throw new RuntimeException("Unknown operator (" + operator + ") for binary Number expression");
			}
		}

		return new StringValue(left.asString() + right.asString()); // concat
	}
	
	@Override
	public String toString() {
		return String.format("%s %s %s", left_expression.toString(), operator, right_expression.toString());
	}
}

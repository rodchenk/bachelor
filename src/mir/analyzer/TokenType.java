package mir.analyzer;

public enum TokenType {
	EOL, EOF, // ; end of file TODO
	ALLOC, STAR, PLUS, MINUS, SLASH, // operators
	GT, LT, EQ, GTEQ, LTEQ, NOTEQ,/// conditions < > == >= <= !=
	
	TRUE, FALSE, PRINT, IF, ELSE, FOR, // key words
	
	TEXT, // ["*"]
	NUMBER, // int und floating number
	ID, // variable and function names
	
	LPT, RPT, // ( and )
	LCB, RCB, // { and }
	LSB, RSB, // [and ]
}

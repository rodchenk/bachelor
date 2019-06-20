package mir.analyzer;

public enum TokenType {
	EOL, EOF, // ; end of file TODO
	ALLOC, STAR, PLUS, MINUS, SLASH, MODULO, COLON, COMMA, // operators
	GT, LT, EQ, GTEQ, LTEQ, NOTEQ,/// conditions < > == >= <= !=
	
	TRUE, FALSE, PRINT, IF, ELSE, FOR, WHILE, END, AND, OR, CONTINUE,// key words
	NUM, BOOLEAN, STRING, //primitive data types
	TEXT, // ["*"]
	NUMBER, // int und floating number
	ID, // variable and function names
	CONST, //modifier
	LPT, RPT, // ( and )
	LCB, RCB, // { and }
	LSB, RSB, // [and ]
}

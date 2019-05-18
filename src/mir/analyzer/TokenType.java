package mir.analyzer;

public enum TokenType {
	EOL, // ;
	EOF, // ""
	OPER, // +-*/
	STAR,
	PLUS,
	MINUS,
	SLASH,
	
	ALLOC, // =
	
	PRINT,
	
	ONC, // one line comment with #
	
	NUMBER, // [0-9] // TODO floating number
	ID, //internal or externak key words
	
	TEXT, // ["a-zA-z"]
	UNKN, 
	
	LPT, RPT, // ( and )
	LCB, RCB, // { and }
	LSB, RSB, // [and ]
	BRACKET, // sammeltyp fuer alle klammer
}

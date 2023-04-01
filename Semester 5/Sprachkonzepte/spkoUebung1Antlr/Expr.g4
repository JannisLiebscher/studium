// Expr.g4
lexer grammar Expr;
TIME: 			MIDDAY_SHORT | MIDDAY | HOUR':'MINUTE' 'MERIDIEM;

fragment MIDDAY:	('12 '('noon'|'midnight'))|('12:0'[0-1]' 'MERIDIEM);
fragment MIDDAY_SHORT: 	'Noon'|'Midnight';
fragment HOUR: 		[0-9]|'11';
fragment MINUTE: 	[0-5][0-9];
fragment MERIDIEM: 	'a.m.'|'p.m.';
WS: 			[ \t\r\n]+ -> skip;
grammar LExpr;

s : e ;

e : LPAREN e RPAREN         # LRParen
  | <assoc=right> e POW e   # Pow
  | SUB e                   # Negation
  | e op=(MULT|DIV) e 		# MultDiv
  | e op=(ADD|SUB) e 		# AddSub
  | e NEWLINE e             # NewLineEx
  | DOUBLE        		    # Double
  | e NEWLINE                 # NewLine
  ;

POW : '^' ;
MULT: '*' ;
DIV : '/' ;
ADD : '+' ;
SUB : '-' ;
LPAREN: '(' ;
RPAREN: ')' ;
NEWLINE: '\n';
DOUBLE : [0-9]+(('.'[0-9]+)?) ;
WS : [ \t]+ -> skip ;


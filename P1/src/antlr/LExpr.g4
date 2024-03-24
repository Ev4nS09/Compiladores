grammar LExpr;

s : instruction+;

instruction : ins=INSTRUCTION e;

e : LPAREN e RPAREN         # LRParen
  | <assoc=right> e POW e   # Pow
  | SUB e                   # Negation
  | e op=(MULT|DIV) e 		# MultDiv
  | e op=(ADD|SUB) e 		# AddSub
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
INSTRUCTION: 'print';
DOUBLE : [0-9]+(('.'[0-9]+)?) ;
WS : [ \t]+ -> skip ;


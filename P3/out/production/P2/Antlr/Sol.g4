grammar Sol;

sol : declaration* line* EOF
    ;

declaration : TYPE labelExpression (',' labelExpression)* ';'
            ;

line : block | affectation ';' | instruction ';' | if | loop | break | ';'
     ;

break : BREAK ';'
      ;

loop : 'while' expression 'do' line                                                     #While
     | 'for' affectation 'to' (expression) 'do' line                                    #For
     ;

if : 'if' expression 'then' line ('else' line)?
   ;

block : 'begin' (line)* 'end'
      ;


labelExpression : LABEL ('=' expression)?
                ;

affectation: (LABEL '=' expression)
            ;

instruction : PRINT expression
            ;

expression : '(' expression ')'                                             #LRParen
  | op=('not' | '-') expression                                             #Unary
  | expression op=('*' | '/' | '%') expression 		                        #MultDivMod
  | expression op=('+' | '-') expression 		                            #AddSub
  | expression op=('>' | '<' | '>=' | '<=') expression                      #Relational
  | expression op=('==' | '!=') expression                                  #Iguality
  | expression op='and' expression                                          #And
  | expression op='or' expression                                           #Or
  | LABEL                                                                   #Lable
  | DOUBLE         		                                                    #Double
  | INT                                                                     #Int
  | BOOL                                                                    #Bool
  | STRING                                                                  #String
  ;


BREAK: 'break';
TYPE: 'int' | 'real' | 'string' | 'bool';
PRINT: 'print';
BOOL: 'true' | 'false';
INT: [0-9]+;
DOUBLE: [0-9]+(('.'[0-9]+)?);
STRING: '"' ('\\"' | .)*? '"';
LABEL: [_a-zA-Z]([a-zA-Z0-9_]*);
WS : [ \n\t\r]+ -> skip ;

SL_COMMENT : '//' .*? (EOF|'\n') -> skip; // single-line comment
ML_COMMENT : '/*' .*? '*/' -> skip ; // multi-line comment

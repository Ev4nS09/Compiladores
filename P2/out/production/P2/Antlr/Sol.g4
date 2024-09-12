grammar Sol;

sol : instruction+ ;

instruction : PRINT expression ';';

expression : '(' expression ')'                                       #LRParen
  | op=('not' | '-') expression                                             #Unary
  | expression op=('*' | '/' | '%') expression 		                        #MultDivMod
  | expression op=('+' | '-') expression 		                            #AddSub
  | expression op=('>' | '<' | '>=' | '<=') expression                      #Relational
  | expression op=('==' | '!=') expression                                  #Iguality
  | expression op='and' expression                                          #And
  | expression op='or' expression                                           #Or
  | DOUBLE         		                                                    #Double
  | INT                                                                     #Int
  | BOOL                                                                    #Bool
  | STRING                                                                  #String
  ;


PRINT: 'print';
BOOL: 'true' | 'false';
INT: [0-9]+;
DOUBLE: [0-9]+(('.'[0-9]+)?);
STRING: '"' ('\\"' | .)*? '"';
WS : [ \n\t\r]+ -> skip ;

SL_COMMENT : '//' .*? (EOF|'\n') -> skip; // single-line comment
ML_COMMENT : '/*' .*? '*/' -> skip ; // multi-line comment
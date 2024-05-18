grammar Tasm;

tasm: line+ ( '\r' | '\n')? EOF;

line:       instruction ('\n'|'\r')                         #Inst
           | TAG(',' TAG)* ':' instruction ('\n'|'\r')      #TagInstruction
           ;



instruction : constant

            | allocation

            | call

            | return

            | pop

            | condition

            | cast

            | operation

            | printf

            | ('\n' | '\r')

            | HALT

            | jump

            ;

constant: ICONST INT                                #Iconst
          | DCONST (DOUBLE | INT)                   #Dconst
          | SCONST STRING                           #Sconst
          | TCONST                                  #Tconst
          | FCONST                                  #Fconst
        ;



allocation: alloc=(GALLOC | GLOAD | GSTORE) INT                                                    #Global
          | alloc=(LALLOC | LLOAD | LSTORE) INT                                                    #Local
          ;

call: CALL TAG
    ;

return: ret=(RET | RETVAL) INT
      ;

pop: POP INT
   ;

condition: cd=(IEQ | INEQ | ILT | ILEQ | DEQ| DNEQ | DLEQ | DLT | DTOS |
                      SEQ | SNEQ | BEQ | BNEQ| AND | OR | NOT)                                         #Conditions
         ;

cast: change=(ITOD | ITOS | DTOS | BTOS)                                                                      #Change
    ;

operation: op=(IUMINUS | IADD | ISUB | IMULT | IDIV | IMOD |
                DUMINUS| DADD | DSUB | DMULT | DDIV |
                 SADD)                                                                           #Operations
         ;

printf: print=(IPRINT | DPRINT | SPRINT | BPRINT)                                                  #Print
      ;

jump: jp=(JUMP | JUMPF | JUMPT) tag=TAG                                                           #Jp
    ;


HALT: 'halt';
BOOL: 'true' | 'false';
INT: ([0-9]+) | ('-' [0-9]+);
DOUBLE: [0-9]+(('.'[0-9]+)?);
STRING: '"' ('\\"' | .)*? '"';

ICONST:'iconst';
IPRINT:'iprint';
IUMINUS:'iuminus';
IADD:'iadd';
ISUB:'isub';
IMULT:'imult';
IDIV:'idiv';
IMOD:'imod';
IEQ:'ieq';
INEQ:'ineq';
ILT:'ilt';
ILEQ:'ileq';
ITOD:'itod';
ITOS:'itos';
DCONST:'dconst';
DPRINT:'dprint';
DUMINUS:'duminus';
DADD:'dadd';
DSUB:'dsub';
DMULT:'dmult';
DDIV:'ddiv';
DEQ:'deq';
DNEQ:'dneq';
DLT:'dlt';
DLEQ:'dleq';
DTOS:'dtos';
SCONST:'sconst';
SPRINT:'sprint';
SADD:'sadd';
SEQ:'seq';
SNEQ:'sneq';
TCONST:'tconst';
FCONST:'fconst';
BPRINT:'bprint';
BEQ:'beq';
BNEQ:'bneq';
AND:'and';
OR:'or';
NOT:'not';
BTOS:'btos';
JUMP:'jump';
JUMPT:'jumpt';
JUMPF:'jumpf';
GALLOC:'galloc';
GLOAD:'gload';
GSTORE:'gstore';
LALLOC:'lalloc';
LLOAD:'lload';
LSTORE:'lstore';
POP:'pop';
CALL:'call';
RETVAL:'retval';
RET:'ret';

TAG: [_a-zA-Z]([a-zA-Z0-9_-]*);

WS : [ \t\n\r]+ -> skip ;
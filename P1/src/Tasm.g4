grammar Tasm;

tasm: (instruction)+ HALT | HALT
;

instruction : (ICONST INT | DCONST DOUBLE | SCONST STRING | BCONST BOOL)           '\n'                      #Const

            | alloc=(GALLOC | GLOAD | GSTORE ) INT                             '\n'                          #Global

            | condition=(IEQ | INEQ | ILT | ILEQ | DEQ| DNEQ | DLT | DTOS |
                         SEQ | SNEQ | BEQ | BNEQ| AND | OR | NOT)            '\n'                             #Conditions

            | change=(ITOD | ITOS | DTOS | BTOS)                     '\n'                                     #Change

            | operation=(IUMINUS | IADD | ISUB | IMULT | IDIV | IMOD |
                         DUMINUS| DADD | DSUB | DMULT | DDIV |
                         SADD)                                   '\n'                                         #Operations

            | print=(IPRINT | DPRINT | SPRINT | BPRINT)                  '\n'                                 #Print

            | jump=(JUMP | JUMPF | JUMPT) TAG2           '\n'                                                  #Jump

            | tag=TAG instruction                                                                        #Taga
            ;


HALT: 'halt';
BOOL: 'true' | 'false';
INT: [0-9]+;
DOUBLE: [0-9]+(('.'[0-9]+)?);
STRING: '"' .*? '"';
TAG: [a-zA-Z]([a-zA-Z0-9_-]*)':';

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
BCONST:'bconst';
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

TAG2: [a-zA-Z]([a-zA-Z0-9_-]*);

WS : [ \t]+ -> skip ;
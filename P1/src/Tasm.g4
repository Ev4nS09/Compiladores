grammar Tasm;

s: instruction+ HALT | HALT
;

instruction : TAG ':' instruction                                                                        #Tag

            | (ICONST INT | DCONST DOUBLE | SCONST STRING | BCONST BOOL)                                 #Const

            | alloc=(GALLOC | GLOAD | GSTORE ) INT                                                       #Global

            | condition=(IEQ | INEQ | ILT | ILEQ | DEQ| DNEQ | DLT | DTOS |
                         SEQ | SNEQ | BEQ | BNEQ| AND | OR | NOT)                                        #Conditions

            | change=(ITOD | ITOS | DTOS | BTOS)                                                         #Change

            | operation=(IUMINUS | IADD | ISUB | IMULT | IDIV | IMOD |
                         DUMINUS| DADD | DSUB | DMULT | DDIV |
                         SADD)                                                                           #Operations

            | print=(IPRINT | DPRINT | SPRINT | BPRINT)                                                  #Print

            | jump=(JUMP | JUMPF | JUMPT) TAG                                                            #Jump
            ;



TAG: 'beginLoop' | 'endLoop';
BOOL: 'true' | 'false';
INT: [0-9]+;
DOUBLE: [0-9]+(('.'[0-9]+)?);
STRING: '"' .*? '"';

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
HALT: 'halt';


WS : [ \n\t]+ -> skip ;
public enum OpCode
{
    iconst, iadd, imult, idiv, isub, iuminus, imod, iprint, ieq, ineq, ilt, ileq, itod, itos,
    dconst, dadd, dmult, ddiv, dsub, suminus, dprint, deq, dneq, dlt, dleq, dtos,
    sconst, sadd, sprint, seq, sneq,
    bconst, bprint, beq, bneq, btos,
    galloc, gload, gstore,
    jump, jumpt, jumpf,
    halt
}

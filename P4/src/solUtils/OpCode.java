package solUtils;

public enum OpCode
{
    iconst, iadd, imult, idiv, isub, iuminus, imod, iprint, ieq, ineq, ilt, ileq, itod, itos,
    dconst, dadd, dmult, ddiv, dsub, duminus, dprint, deq, dneq, dlt, dleq, dtos,
    sconst, sadd, sprint, seq, sneq,
    bprint, beq, bneq, btos, tconst, fconst, or, and, not,
    galloc, gload, gstore, lalloc, lload, lstore,
    pop, call, ret, retval,
    jump, jumpt, jumpf,
    halt
}

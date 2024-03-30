public enum OpCode
{
    iconst, iadd, imult, idiv, isub, iuminus, imod, iprint, ieq, ineq, ilt, ileq, itod, itos,
    dconst, dadd, dmult, ddiv, dsub, duminus, dprint, deq, dneq, dlt, dleq, dtos,
    sconst, sadd, sprint, seq, sneq,
    bprint, beq, bneq, btos, tconst, fconst,
    galloc, gload, gstore,
    jump, jumpt, jumpf,
    halt, pool
}

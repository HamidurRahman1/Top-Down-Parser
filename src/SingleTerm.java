
public class SingleTerm extends E
{
    // Term term; inherited from E

    SingleTerm(Term t)
    {
        term = t;
    }

    void printParseTree(String indent)
    {
        IO.displayln(indent + indent.length() + " <E>");
        term.printParseTree(indent+" ");
    }

//    Val Eval(HashMap<String,Val> state)
//    {
//        return term.Eval(state);
//    }
//
//    void emitInstructions()
//    {
//        term.emitInstructions();
//    }
}
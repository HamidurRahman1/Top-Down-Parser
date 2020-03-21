
class SinglePrimary extends Term
{
    // Primary primary; inherited from Term

    SinglePrimary(Primary p)
    {
        primary = p;
    }

    void printParseTree(String indent)
    {
        IO.displayln(indent + indent.length() + " <term>");
        primary.printParseTree(indent+" ");
    }

//    Val Eval(HashMap<String,Val> state)
//    {
//        return primary.Eval(state);
//    }
//
//    void emitInstructions()
//    {
//        primary.emitInstructions();
//    }
}
package parser;

public class FunOpPair extends FunOp
{
    @Override
    public void printParseTree(String indent)
    {
        IO.displayln(indent + indent.length() + " pair");
    }
}

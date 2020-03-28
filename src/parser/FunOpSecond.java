package parser;

public class FunOpSecond extends FunOp
{
    @Override
    public void printParseTree(String indent)
    {
        IO.displayln(indent + indent.length() + " second");
    }
}

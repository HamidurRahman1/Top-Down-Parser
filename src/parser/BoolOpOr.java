package parser;

public class BoolOpOr extends BoolOp
{
    @Override
    public void printParseTree(String indent)
    {
        IO.displayln(indent + indent.length() + " or");
    }
}

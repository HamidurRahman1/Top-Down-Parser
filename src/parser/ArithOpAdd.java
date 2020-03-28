package parser;

public class ArithOpAdd extends ArithOp
{
    @Override
    public void printParseTree(String indent)
    {
        IO.displayln(indent + indent.length() + " +");
    }
}

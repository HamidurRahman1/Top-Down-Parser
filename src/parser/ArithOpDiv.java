package parser;

public class ArithOpDiv extends ArithOp
{
    @Override
    public void printParseTree(String indent)
    {
        IO.displayln(indent + indent.length() + " /");
    }
}

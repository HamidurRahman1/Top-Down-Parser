package parser;

public class ArithOpMul extends ArithOp
{
    @Override
    public void printParseTree(String indent)
    {
        IO.displayln(indent + indent.length() + " *");
    }
}

package parser;

public class CompOpEq extends CompOp
{
    @Override
    public void printParseTree(String indent)
    {
        IO.displayln(indent + indent.length() + " =");
    }
}

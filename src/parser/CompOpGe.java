package parser;

public class CompOpGe extends CompOp
{
    @Override
    public void printParseTree(String indent)
    {
        IO.displayln(indent + indent.length() + " >=");
    }
}

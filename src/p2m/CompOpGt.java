package p2m;

public class CompOpGt extends CompOp
{
    @Override
    public void printParseTree(String indent)
    {
        IO.displayln(indent + indent.length() + " >");
    }
}

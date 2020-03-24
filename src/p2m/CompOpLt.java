package p2m;

public class CompOpLt extends CompOp
{
    @Override
    public void printParseTree(String indent)
    {
        IO.displayln(indent + indent.length() + " <");
    }
}

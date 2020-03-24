package p2m;

public class CompOpGe extends CompOp
{
    @Override
    public void printParseTree(String indent)
    {
        IO.displayln(indent + indent.length() + " <");
    }
}

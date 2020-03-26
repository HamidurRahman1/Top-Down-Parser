package p2m;

public class ArithOpSub extends ArithOp
{
    @Override
    public void printParseTree(String indent)
    {
        IO.displayln(indent + indent.length() + " -");
    }
}

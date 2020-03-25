package p2m;

public class BoolOpNot extends BoolOp
{
    @Override
    public void printParseTree(String indent)
    {
        IO.displayln(indent + indent.length() + " or");
    }
}

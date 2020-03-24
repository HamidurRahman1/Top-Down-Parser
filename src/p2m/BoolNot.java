package p2m;

public class BoolNot extends BoolOp
{
    @Override
    public void printParseTree(String indent)
    {
        IO.displayln(indent + indent.length() + " or");
    }
}

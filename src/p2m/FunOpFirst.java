package p2m;

public class FunOpFirst extends FunOp
{
    @Override
    public void printParseTree(String indent)
    {
        IO.displayln(indent + indent.length() + " first");
    }
}

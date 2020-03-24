package p2m;

public class FunOpId extends FunOp
{
    public String funOpId;

    public FunOpId(String funOpId)
    {
        this.funOpId = funOpId;
    }

    @Override
    public void printParseTree(String indent)
    {
        IO.displayln(indent + indent.length() + " " + funOpId);
    }
}

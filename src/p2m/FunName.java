package p2m;

public class FunName extends BaseFunName
{
    public String id;

    public FunName(String id)
    {
        this.id = id;
    }

    @Override
    public void printParseTree(String indent)
    {
        IO.displayln(indent + indent.length() + " <fun name> " + id);
    }
}

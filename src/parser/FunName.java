package parser;

public class FunName
{
    public String id;

    public FunName(String id)
    {
        this.id = id;
    }

    public void printParseTree(String indent)
    {
        IO.displayln(indent + indent.length() + " <fun name> " + id);
    }
}

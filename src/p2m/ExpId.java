package p2m;

public class ExpId extends Exp
{
    public String expId;

    public ExpId(String expId)
    {
        this.expId = expId;
    }

    @Override
    public void printParseTree(String indent)
    {
        IO.displayln(indent + indent.length() + " <exp>");

        indent += " ";

        IO.displayln(indent + indent.length() + " " + expId);
    }
}

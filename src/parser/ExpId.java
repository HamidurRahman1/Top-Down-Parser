package parser;

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
        printExp(indent);

        indent += " ";

        IO.displayln(indent + indent.length() + " " + expId);
    }
}

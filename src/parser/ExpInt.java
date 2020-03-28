package parser;

public class ExpInt extends Exp
{
    public int expInt;

    public ExpInt(int expInt)
    {
        this.expInt = expInt;
    }

    @Override
    public void printParseTree(String indent)
    {
        printExp(indent);

        indent += " ";

        IO.displayln(indent + indent.length() + " " + expInt);
    }
}

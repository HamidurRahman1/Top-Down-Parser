package parser;

public class ExpFloat extends Exp
{
    public float expFloat;

    public ExpFloat(float expFloat)
    {
        this.expFloat = expFloat;
    }

    @Override
    public void printParseTree(String indent)
    {
        printExp(indent);

        indent += " ";

        IO.displayln(indent + indent.length() + " " + expFloat);
    }
}

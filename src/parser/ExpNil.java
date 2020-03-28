package parser;

public class ExpNil extends Exp
{
    @Override
    public void printParseTree(String indent)
    {
        printExp(indent);

        indent += " ";

        IO.displayln(indent + indent.length() + " nil");
    }
}

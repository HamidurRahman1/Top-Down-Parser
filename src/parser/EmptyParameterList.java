package parser;

public class EmptyParameterList extends ParameterList
{
    @Override
    public void printParseTree(String indent)
    {
        IO.displayln(indent + indent.length() + " <parameter list>");
    }
}

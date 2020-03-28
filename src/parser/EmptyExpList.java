package parser;

public class EmptyExpList extends ExpList
{
    @Override
    public void printParseTree(String indent)
    {
        IO.displayln(indent + indent.length() + " <exp list>");
    }
}

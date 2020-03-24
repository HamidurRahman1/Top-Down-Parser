package p2m;

public class Header extends BaseHeader
{
    public FunName funName;
    public ParameterList parameterList;

    public Header(FunName funName)
    {
        this.funName = funName;
    }

    public Header(FunName funName, ParameterList parameterList)
    {
        this.funName = funName;
        this.parameterList = parameterList;
    }

    @Override
    public void printParseTree(String indent)
    {
        IO.displayln(indent + indent.length() + " <header>");

        String indent1s = indent + " ";

        funName.printParseTree(indent1s);

        if(parameterList != null)
        {
            parameterList.printParseTree(indent1s);
        }
        else
        {
            IO.displayln(indent1s + indent1s.length() + " <parameter list>");
        }
    }
}

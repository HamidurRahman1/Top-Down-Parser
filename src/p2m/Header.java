package p2m;

public class Header extends BaseHeader
{
    public FunName funName;
    public ParameterList parameterList;

    public Header(FunName funName, ParameterList parameterList)
    {
        this.funName = funName;
        this.parameterList = parameterList;
    }

    @Override
    public void printParseTree(String indent)
    {

    }
}

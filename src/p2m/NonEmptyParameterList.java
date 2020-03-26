package p2m;

public class NonEmptyParameterList extends ParameterList
{
    public String parameterId;
    public ParameterList parameterList;

    public NonEmptyParameterList(String parameterId, ParameterList parameterList)
    {
        this.parameterId = parameterId;
        this.parameterList = parameterList;
    }

    @Override
    public void printParseTree(String indent)
    {
        IO.displayln(indent + indent.length() + " <parameter list>");

        String indent1 = indent + " ";

        IO.displayln(indent1 + indent1.length() + " " + parameterId);

        if(parameterList instanceof NonEmptyParameterList)
        {
            printMultipleParameters(indent1, (NonEmptyParameterList) parameterList);
        }
    }

    public void printMultipleParameters(String indent, NonEmptyParameterList paramList)
    {
        if(paramList == null)
        {
            return;
        }

        IO.displayln(indent + indent.length() + " " + paramList.parameterId);

        if(paramList.parameterList instanceof NonEmptyParameterList)
        {
            printMultipleParameters(indent, (NonEmptyParameterList) paramList.parameterList);
        }
    }
}

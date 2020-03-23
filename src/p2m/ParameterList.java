package p2m;

public class ParameterList extends BaseParameterList
{
    public ParameterId parameterId;
    public ParameterList parameterList;

    public ParameterList(ParameterId parameterId)
    {
        this.parameterId = parameterId;
    }

    public ParameterList(ParameterId parameterId, ParameterList parameterList)
    {
        this.parameterId = parameterId;
        this.parameterList = parameterList;
    }

    @Override
    public void printParseTree(String indent)
    {

    }
}

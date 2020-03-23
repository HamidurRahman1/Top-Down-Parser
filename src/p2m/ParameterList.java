package p2m;

import java.util.List;

public class ParameterList extends BaseParameterList
{
//    public ParameterId parameterId;
//    public ParameterList parameterList;

    public List<ParameterId> parameterIdList;

    public ParameterList()
    {
        this.parameterIdList = null;
    }

    public ParameterList(List<ParameterId> parameterIdList)
    {
        this.parameterIdList = parameterIdList;
    }

    @Override
    public void printParseTree(String indent)
    {

    }
}

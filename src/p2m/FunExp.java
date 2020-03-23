package p2m;

import java.util.List;

public class FunExp extends BaseFunExp
{
    public FunOp funOp;
//    public ExpList expList;
    public List<ExpList> expList;

    public FunExp(FunOp funOp, List<ExpList> expList)
    {
        this.funOp = funOp;
        this.expList = expList;
    }

    @Override
    public void printParseTree(String indent)
    {

    }
}

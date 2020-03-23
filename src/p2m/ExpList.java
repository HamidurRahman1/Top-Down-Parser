package p2m;

import java.util.List;

public class ExpList extends BaseExpList
{
    // Optionally class or just id
//    public ExpList expList;

    public List<Exp> exps;

    public ExpList(List<Exp> exps)
    {
        this.exps = exps;
    }

    @Override
    public void printParseTree(String indent)
    {

    }
}

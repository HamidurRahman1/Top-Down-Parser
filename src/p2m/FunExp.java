package p2m;

public class FunExp extends Exp
{
    public FunOp funOp;
    public ExpList exp;

    public FunExp(FunOp funOp, ExpList exp)
    {
        this.funOp = funOp;
        this.exp = exp;
    }

    @Override
    public void printParseTree(String indent)
    {

    }
}

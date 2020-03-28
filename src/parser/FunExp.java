package parser;

public class FunExp extends Exp
{
    public FunOp funOp;
    public ExpList expList;

    public FunExp(FunOp funOp, ExpList expList)
    {
        this.funOp = funOp;
        this.expList = expList;
    }

    @Override
    public void printParseTree(String indent)
    {
        IO.displayln(indent + indent.length() + " <exp>");

        indent += " ";

        IO.displayln(indent + indent.length() + " <fun exp>");

        funOp.printParseTree(indent + " ");
        expList.printParseTree(indent + " ");
    }
}

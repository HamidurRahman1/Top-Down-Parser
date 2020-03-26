package p2m;

public class NonEmptyExpList extends ExpList
{
    public Exp exp;
    public ExpList expList;

    public NonEmptyExpList(Exp exp, ExpList expList)
    {
        this.exp = exp;
        this.expList = expList;
    }

    @Override
    public void printParseTree(String indent)
    {
        IO.displayln(indent + indent.length() + " <exp list>");

        exp.printParseTree(indent + " ");

        if(expList instanceof NonEmptyExpList)
        {
            printMultipleParameters(indent + " ", (NonEmptyExpList) expList);
        }
    }

    public void printMultipleParameters(String indent, NonEmptyExpList expList1)
    {
        if(expList1 == null)
        {
            return;
        }

        expList1.exp.printParseTree(indent);

        if(expList1.expList instanceof NonEmptyExpList)
        {
            printMultipleParameters(indent, (NonEmptyExpList) expList1.expList);
        }
    }
}

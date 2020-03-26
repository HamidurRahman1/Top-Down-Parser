package p2m;

public class ExpIfThenElse extends Exp
{
    public Exp ifExp;
    public Exp thenExp;
    public Exp elseExp;

    public ExpIfThenElse(Exp ifExp, Exp thenExp, Exp elseExp)
    {
        this.ifExp = ifExp;
        this.thenExp = thenExp;
        this.elseExp = elseExp;
    }

    @Override
    public void printParseTree(String indent)
    {
        printExp(indent);
        indent += " ";

        IO.displayln(indent + indent.length() + " if");
        ifExp.printParseTree(indent + " ");

        IO.displayln(indent + indent.length() + " then");
        thenExp.printParseTree(indent + " ");

        IO.displayln(indent + indent.length() + " else");
        elseExp.printParseTree(indent + " ");
    }
}

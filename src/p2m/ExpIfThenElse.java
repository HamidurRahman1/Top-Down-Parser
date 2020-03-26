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
        String sLength = indent + " ";

        IO.displayln(sLength + sLength.length() + " if");
        ifExp.printParseTree(sLength + " ");

        IO.displayln(sLength + sLength.length() + " then");
        thenExp.printParseTree(sLength + " ");

        IO.displayln(sLength + sLength.length() + " else");
        elseExp.printParseTree(sLength + " ");
    }
}

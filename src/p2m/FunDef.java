package p2m;

public class FunDef extends BaseFunDefList
{
     public Header header;
     public Exp exp;

    public FunDef(Header header, Exp exp)
    {
        this.header = header;
        this.exp = exp;
    }

    @Override
    public void printParseTree(String indent)
    {
        IO.displayln(indent + indent.length() + " <fun def>");

        if(header != null)
        {
            header.printParseTree(indent + " ");
        }
        if(exp != null)
        {
            exp.printParseTree(indent + " ");
        }

        IO.displayln(indent);
    }
}

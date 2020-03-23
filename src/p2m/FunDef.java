package p2m;

public class FunDef extends BaseFunDef
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

    }
}

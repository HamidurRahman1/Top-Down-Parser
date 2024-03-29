package parser;

public class FunDefList extends BaseFunDefList
{
    public FunDef funDef;
    public FunDefList funDefList;

    public FunDefList(FunDef funDef)
    {
        this.funDef = funDef;
    }

    public FunDefList(FunDef funDef, FunDefList funDefList)
    {
        this.funDef = funDef;
        this.funDefList = funDefList;
    }

    @Override
    public void printParseTree(String indent)
    {
        if(funDef != null)
        {
            funDef.printParseTree(indent);
        }
        if(funDefList != null)
        {
            funDefList.printParseTree(indent);
        }
    }
}

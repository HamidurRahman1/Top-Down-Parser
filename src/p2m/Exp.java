package p2m;

public abstract class Exp extends BaseFunDef
{
    public void printExp(String indent)
    {
        IO.displayln(indent + indent.length() + " <exp>");
    }
}
